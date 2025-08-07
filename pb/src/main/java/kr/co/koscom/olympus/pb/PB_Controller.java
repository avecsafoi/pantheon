package kr.co.koscom.olympus.pb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.include.PB_ST;
import kr.co.koscom.olympus.pb.include.PB_Service;
import kr.co.koscom.olympus.pb.include.hdr.PB_HdrAccount;
import kr.co.koscom.olympus.pb.include.hdr.PB_HdrCommon;
import kr.co.koscom.olympus.pb.include.hdr.PB_Json;
import kr.co.koscom.olympus.pb.include.io.PB_TextDataInputStream;
import kr.co.koscom.olympus.pb.include.io.PB_TextDataOutputStream;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import static kr.co.koscom.olympus.pb.include.PB_COMMON.SUCCESS;

@Controller("/pb")
public class PB_Controller {

    @Resource
    private ApplicationContext ctx;

    @Resource
    private ObjectMapper om;

    @PostMapping("/json")
    public @ResponseBody PB_Json gatewayJson(@RequestBody PB_Json jio) throws Throwable {
        PB_HdrAccount ha = jio.getHdrAccount();
        PB_Service<?> svc = (PB_Service<?>) ctx.getBean("PB_SID " + ha.getASvcId());
        Method m = Arrays.stream(svc.getClass().getMethods()).filter(x -> x.getName().equals("call") && x.getParameterCount() == 1).findAny().orElseThrow();

        Class<?> cst = m.getParameters()[0].getType();
        Type[] ta = ((ParameterizedType) cst.getGenericSuperclass()).getActualTypeArguments();
        Class<?> cin = (Class<?>) ta[0];
        Class<?> cout = (Class<?>) ta[1];

        Object in = jio.getData() == null ? cin.getConstructor().newInstance() : om.convertValue(jio.getData(), cin);
        Object out = cout.getConstructor().newInstance();

        @SuppressWarnings("unchecked")
        PB_ST<Object, Object> st = (PB_ST<Object, Object>) cst.getConstructor().newInstance();
        st.setHdrAccount(ha);
        st.setIn(in);
        st.setOut(out);
        m.invoke(svc, st);
        Map<String, Object> map = om.convertValue(st.getOut(), new TypeReference<>() {
        });
        jio.setData(map);

        return jio;
    }

    @PostMapping("/text")
    public @ResponseBody String gatewayText(@RequestBody String s) throws Throwable {
        return new String(gatewayBytes(s.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    @PostMapping("/bytes")
    public @ResponseBody byte[] gatewayBytes(@RequestBody byte[] ib) throws Throwable {

        ByteArrayInputStream bais = new ByteArrayInputStream(ib);
        PB_TextDataInputStream dis = new PB_TextDataInputStream(bais);

        PB_HdrCommon hc = dis.readObject(PB_HdrCommon.class);
        if (hc.getATgLen() != ib.length)
            throw new RuntimeException("전문의 길이가 일치하지 않습니다. (%d != %d)".formatted(hc.getATgLen(), ib.length));
        PB_HdrAccount ha = dis.readObject(PB_HdrAccount.class);
        PB_Service<?> svc = (PB_Service<?>) ctx.getBean("PB_SID " + ha.getASvcId());
        Method m = Arrays.stream(svc.getClass().getMethods()).filter(x -> x.getName().equals("call") && x.getParameterCount() == 1).findAny().orElseThrow();

        Class<?> cst = m.getParameters()[0].getType();
        Type[] ta = ((ParameterizedType) cst.getGenericSuperclass()).getActualTypeArguments();
        Class<?> cin = (Class<?>) ta[0];
        Class<?> cout = (Class<?>) ta[1];

        Object in = dis.readObject(cin); // 요청자료 읽기
        Object out = cout.getConstructor().newInstance();

        @SuppressWarnings("unchecked")
        PB_ST<Object, Object> st = (PB_ST<Object, Object>) cst.getConstructor().newInstance();
        st.setHdrAccount(ha);
        st.setIn(in);
        st.setOut(out);
        m.invoke(svc, st);

        int ri = (int) m.invoke(svc, st);
        if (ri != SUCCESS) throw new RuntimeException("전문처리 중 오류가 발생하였습니다.");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
        PB_TextDataOutputStream dos = new PB_TextDataOutputStream(baos);
        dos.writeObject(hc); // 공통 헤더
        dos.writeObject(ha); // 계정계 헤더
        dos.writeObject(out); // 응답자료
        dos.close();

        byte[] rb = baos.toByteArray();
        ByteBuffer bb = ByteBuffer.wrap(rb);
        bb.putInt(0, rb.length); // 전문길이 설정

        return rb;
    }
}
