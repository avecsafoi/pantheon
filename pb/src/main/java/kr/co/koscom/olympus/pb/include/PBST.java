package kr.co.koscom.olympus.pb.include;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataOutputStream;
import kr.co.koscom.olympus.pb.include.hdr.PBHdrAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.IOException;
import java.lang.reflect.Field;

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
        this.hdrCommon = new PBHdrAccount();
        this.hdrAccount = new PBHdrAccount();
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
    public void readPBData(PBDataInputStream in) throws IOException {
//        this.hdrCommon = in.readObject(PBHdrAccount.class);
//        this.hdrAccount = in.readObject(PBHdrAccount.class);
//        String svcId = this.hdrAccount.getASvcId();
        super.readPBData(in);
    }

    @Override
    public void writePBData(PBDataOutputStream os) throws IOException {
        super.writePBData(os);
    }
}
