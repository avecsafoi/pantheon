package kr.co.koscom.olympus.pb.include;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.include.hdr.PBHdrAccount;
import kr.co.koscom.olympus.pb.include.hdr.PBHdrCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class PBST<I, O> extends PBObject {

    @PBA(name = "공통 헤더")
    public PBHdrCommon hdrCommon;

    @PBA(name = "계정계 헤더")
    public PBHdrAccount hdrAccount;

    @PBA(name = "입력")
    public I in;

    @PBA(name = "출력")
    public O out;
}
