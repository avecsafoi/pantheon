package kr.co.koscom.olympus.pb.include;

import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataOutputStream;
import kr.co.koscom.olympus.pb.ab.util.PBDataUtil;
import kr.co.koscom.olympus.pb.include.hdr.PBHdrAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import static kr.co.koscom.olympus.pb.ab.util.PBDataUtil.createObject;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class PBST<I, O> extends PBObject {

    @PBA(name = "공통 헤더")
    public PBHdrAccount hdrCommon;

    @PBA(name = "계정계 헤더")
    public PBHdrAccount hdrAccount;

    @PBA(name = "입력")
    public I in;

    @PBA(name = "출력")
    public O out;

    public PBST() {
        hdrCommon = new PBHdrAccount();
        hdrAccount = new PBHdrAccount();
        try {
            Field f1 = FieldUtils.getField(getClass(), "in", true);
            Field f2 = FieldUtils.getField(getClass(), "out", true);
            Object o1 = f1.getType().getConstructor().newInstance();
            Object o2 = f2.getType().getConstructor().newInstance();
            f1.set(this, o1);
            f2.set(this, o2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readPBData(PBDataInputStream is) throws IOException {

        hdrCommon = is.readObject(PBHdrAccount.class);
        hdrAccount = is.readObject(PBHdrAccount.class);

        String svcId = hdrAccount.getASvcId();
        PBService<?> svc = PBDataUtil.findPBService(svcId);
        if (svc == null) throw new IOException("Not found PBService for svcId(%s)".formatted(svcId));

        ParameterizedType p1 = PBDataUtil.findInterfaceParameterizedType(svc.getClass(), PBService.class);
        if (p1 == null) throw new IOException("Not found PBST class for svcId(%s)".formatted(svcId));

        PBST<PBData, PBData> st = createObject(p1.getActualTypeArguments()[0]);
        ParameterizedType p2 = PBDataUtil.findInterfaceParameterizedType(st.getClass(), PBST.class);
        if (p2 == null) throw new IOException("Not found IN,OUT class for svcId(%s)".formatted(svcId));

        PBData in = createObject(p2.getActualTypeArguments()[0]);
        PBData out = createObject(p2.getActualTypeArguments()[1]);

        in.readPBData(is);
        out.readPBData(is);
    }

    @Override
    public void writePBData(PBDataOutputStream os) throws IOException {
        os.writeObject(hdrCommon);
        os.writeObject(hdrAccount);
        os.writeObject(in);
        os.writeObject(out);
    }
}
