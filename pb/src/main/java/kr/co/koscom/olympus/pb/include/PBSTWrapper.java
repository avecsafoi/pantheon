package kr.co.koscom.olympus.pb.include;

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
public class PBSTWrapper implements PBST, PBService {

    @PBA(name = "서비스")
    private transient PBService service;

    @PBA(name = "트랜스퍼")
    private transient PBSTImpl st;

    @Override
    public PBHdrAccount getHdrCommon() {
        return st.getHdrCommon();
    }

    @Override
    public PBST setHdrCommon(PBHdrAccount hdrCommon) {
        st.setHdrCommon(hdrCommon);
        return this;
    }

    @Override
    public PBHdrAccount getHdrAccount() {
        return st.getHdrAccount();
    }

    @Override
    public PBST setHdrAccount(PBHdrAccount hdrAccount) {
        st.setHdrAccount(hdrAccount);
        return this;
    }

    @Override
    public Object getIn() {
        return st.getIn();
    }

    @Override
    public PBST setIn(Object in) {
        st.setIn(in);
        return this;
    }

    @Override
    public Object getOut() {
        return st.getOut();
    }

    @Override
    public PBST setOut(Object out) {
        st.setOut(out);
        return this;
    }

    public int process() {
        return service.process(st);
    }

    @Override
    public int process(PBSTImpl st) {
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
