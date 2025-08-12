package kr.co.koscom.olympus.pb.on.bms;

import kr.co.koscom.olympus.pb.include.PBA;
import kr.co.koscom.olympus.pb.include.data.PBObject;
import kr.co.koscom.olympus.pb.include.hdr.PBHdrAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class PB_ST<I, O> extends PBObject {

    @PBA(name = "계정계헤더")
    public PBHdrAccount hdrAccount;

    @PBA(name = "입력")
    public I in;

    @PBA(name = "출력")
    public O out;
}
