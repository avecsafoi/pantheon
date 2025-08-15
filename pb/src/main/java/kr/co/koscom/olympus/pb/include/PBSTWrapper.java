package kr.co.koscom.olympus.pb.include;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataOutputStream;
import kr.co.koscom.olympus.pb.include.hdr.PBHdrAccount;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import static kr.co.koscom.olympus.pb.ab.util.PBDataUtil.*;

@SuppressWarnings({"unchecked", "rawtypes"})
@Accessors(chain = true)
@Data
public class PBSTWrapper implements PBData, PBService {

    @PBA(name = "서비스")
    @JsonIgnore
    private transient PBService service;

    @PBA(name = "트랜스퍼")
    private transient PBST st;

    public int process() {
        return service.process(st);
    }

    @Override
    public int process(PBST st) {
        return service.process(st);
    }

    @Override
    public void readPBData(PBDataInputStream is) throws IOException {

        PBHdrAccount hdrCommon = is.readObject(PBHdrAccount.class);
        PBHdrAccount hdrAccount = is.readObject(PBHdrAccount.class);

        String svcId = hdrAccount.getASvcId();
        service = findPBService(svcId);
        if (service == null) throw new IOException("Not found PBService for svcId(%s)".formatted(svcId));

        ParameterizedType p1 = findInterfaceParameterizedType(service.getClass(), PBService.class);
        if (p1 == null) throw new IOException("Not found PBST class for svcId(%s)".formatted(svcId));

        st = createObject(p1.getActualTypeArguments()[0]);
        st.setHdrCommon(hdrCommon);
        st.setHdrAccount(hdrAccount);

        ParameterizedType p2 = findInterfaceParameterizedType(st.getClass(), PBST.class); // publc class MY_PBST<T> extends PBST 의 T 값 찾기
        if (p2 == null) throw new IOException("Not found IN,OUT class for svcId(%s)".formatted(svcId));

        PBData in = createObject(p2.getActualTypeArguments()[0]);
        PBData out = createObject(p2.getActualTypeArguments()[0]);

        st.setIn(in);
        st.setOut(out);

        in.readPBData(is);
        out.readPBData(is);
    }

    @Override
    public void writePBData(PBDataOutputStream os) throws IOException {
        st.writePBData(os);
    }
}
