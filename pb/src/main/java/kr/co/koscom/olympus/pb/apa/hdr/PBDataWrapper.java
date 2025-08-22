package kr.co.koscom.olympus.pb.apa.hdr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataOutputStream;
import kr.co.koscom.olympus.pb.apa.PBST;
import kr.co.koscom.olympus.pb.apa.PBService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static kr.co.koscom.olympus.pb.ab.util.PBDataUtil.*;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Description("PB Data 송수신 객체")
public class PBDataWrapper extends PBObject {

    @PBA(name = "공통 헤더")
    private PBHdrCommon hdrCommon;

    @PBA(name = "계정계 헤더")
    private PBHdrAccount hdrAccount;

    @PBA(name = "송수신 데이터")
    private Object data;

    @PBA(name = "서비스", skip = true)
    @JsonIgnore
    private transient PBService<PBST<?, ?>> service;

    @PBA(name = "서비스", skip = true)
    @JsonIgnore
    private transient PBST<PBData, PBData> st;

    public void processForJson() throws IOException {
        boolean b = service == null;
        if (b) {
            initService();
            ObjectMapper om = new ObjectMapper();
            om.updateValue(st.getIn(), data);
        }
        service.process(st);
        if (b) data = st.getOut();
    }

    public PBService<PBST<?, ?>> initService() throws IOException {

        String svcId = hdrAccount.getSvcId();
        service = findPBService(svcId);
        if (service == null) throw new IOException("Not found PBService for svcId(%s)".formatted(svcId));

        ParameterizedType p1 = findInterfaceParameterizedType(service.getClass(), PBService.class);
        if (p1 == null) throw new IOException("Not found PBST class for svcId(%s)".formatted(svcId));

        Type t1 = p1.getActualTypeArguments()[0];
        st = createObject(t1);

        st.setHdrCommon(hdrCommon);
        st.setHdrAccount(hdrAccount);

        Type t2 = st.getClass().getGenericSuperclass();
        ParameterizedType p2 = findInterfaceParameterizedType(t2, PBST.class);
        if (p2 == null) throw new IOException("Not found IN,OUT class for svcId(%s)".formatted(svcId));

        PBData in = createObject(p2.getActualTypeArguments()[0]);
        PBData out = createObject(p2.getActualTypeArguments()[1]);

        st.setIn(in);
        st.setOut(out);

        return service;
    }

    @Override
    public void readPBData(PBDataInputStream is) throws IOException {

        hdrCommon = is.readObject(PBHdrCommon.class);
        hdrAccount = is.readObject(PBHdrAccount.class);

        service = initService();

        st.getIn().readPBData(is);
        // out 읽지 않음

        this.setData(st.getIn());
    }

    @Override
    public void writePBData(PBDataOutputStream os) throws IOException {
        hdrCommon.writePBData(os);
        hdrAccount.writePBData(os);
        // in 쓰지 않음
        st.getOut().writePBData(os);

        this.setData(st.getOut());
    }
}
