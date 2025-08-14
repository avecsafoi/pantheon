package kr.co.koscom.olympus.pb.test.pb;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.data.io.PBTextDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBTextDataOutputStream;
import kr.co.koscom.olympus.pb.include.PBST;
import kr.co.koscom.olympus.pb.include.PBService;
import kr.co.koscom.olympus.pb.include.hdr.PBHdrAccount;
import kr.co.koscom.olympus.pb.include.hdr.PBHdrCommon;
import kr.co.koscom.olympus.pb.include.hdr.PBJson;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static kr.co.koscom.olympus.pb.include.PBCommon.SUCCESS;

@RestController
@RequestMapping("/pb/gw")
public class PBGatewayController {

    @Resource
    private ApplicationContext ctx;

    @Resource
    private ObjectMapper om;

    @PostMapping(value = "text"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody PBJson text(@RequestBody PBJson js) throws Throwable {
        PBHdrAccount ha = js.getHdrAccount();
        PBService<?> svc = (PBService<?>) ctx.getBean("PB_SID " + ha.getASvcId());
        Method m = Arrays.stream(svc.getClass().getMethods()).filter(x -> x.getName().equals("process") && x.getParameterCount() == 1).findAny().orElseThrow();

        Class<?> cst = m.getParameters()[0].getType();
        Type[] ta = ((ParameterizedType) cst.getGenericSuperclass()).getActualTypeArguments();
        Class<?> cin = (Class<?>) ta[0];
        Class<?> cout = (Class<?>) ta[1];

        Object in = js.getData() == null ? cin.getConstructor().newInstance() : om.convertValue(js.getData(), cin);
        Object out = cout.getConstructor().newInstance();

        @SuppressWarnings("unchecked")
        PBST<Object, Object> st = (PBST<Object, Object>) cst.getConstructor().newInstance();
        st.setHdrAccount(ha);
        st.setIn(in);
        st.setOut(out);
        m.invoke(svc, st);
//        Map<String, Object> map = om.convertValue(st.getOut(), new TypeReference<>() {
//        });
//        js.setData(map);
        js.setData(out);

        return js;
    }

    @PostMapping("binary")
    public @ResponseBody byte[] binary(@RequestBody byte[] ib) throws Throwable {

        ByteArrayInputStream bais = new ByteArrayInputStream(ib);
        PBTextDataInputStream dis = new PBTextDataInputStream(bais);

        PBHdrCommon hc = dis.readObject(PBHdrCommon.class);
        if (hc.getATgLen() != ib.length)
            throw new RuntimeException("전문의 길이가 일치하지 않습니다. (%d != %d)".formatted(hc.getATgLen(), ib.length));
        PBHdrAccount ha = dis.readObject(PBHdrAccount.class);
        PBService<?> svc = (PBService<?>) ctx.getBean("PB_SID " + ha.getASvcId());
        Method m = Arrays.stream(svc.getClass().getMethods()).filter(x -> x.getName().equals("process") && x.getParameterCount() == 1).findAny().orElseThrow();

        Class<?> cst = m.getParameters()[0].getType();
        Type[] ta = ((ParameterizedType) cst.getGenericSuperclass()).getActualTypeArguments();
        Class<?> cin = (Class<?>) ta[0];
        Class<?> cout = (Class<?>) ta[1];

        Object in = dis.readObject(cin); // 요청자료 읽기
        Object out = cout.getConstructor().newInstance();

        @SuppressWarnings("unchecked")
        PBST<Object, Object> st = (PBST<Object, Object>) cst.getConstructor().newInstance();
        st.setHdrAccount(ha);
        st.setIn(in);
        st.setOut(out);
        m.invoke(svc, st);

        int ri = (int) m.invoke(svc, st);
        if (ri != SUCCESS) throw new RuntimeException("전문처리 중 오류가 발생하였습니다.");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
        PBTextDataOutputStream dos = new PBTextDataOutputStream(baos);
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
