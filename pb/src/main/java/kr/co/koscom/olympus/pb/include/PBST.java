package kr.co.koscom.olympus.pb.include;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataOutputStream;
import kr.co.koscom.olympus.pb.include.hdr.PBHdrAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

    @Override
    public void readPBData(PBDataInputStream in) throws Throwable {
//        this.hdrCommon = in.readObject(PBHdrAccount.class);
//        this.hdrAccount = in.readObject(PBHdrAccount.class);
//        String svcId = this.hdrAccount.getASvcId();
        super.readPBData(in);
    }

    @Override
    public void writePBData(PBDataOutputStream os) throws Throwable {
        super.writePBData(os);
    }
}
