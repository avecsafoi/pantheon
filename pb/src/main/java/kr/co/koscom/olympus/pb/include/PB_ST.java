package kr.co.koscom.olympus.pb.include;

import kr.co.koscom.olympus.pb.include.hdr.PB_HdrAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class PB_ST<I, O> extends PB_Object {

    @PB_A(name = "계정계헤더")
    public PB_HdrAccount hdrAccount;

    @PB_A(name = "입력")
    public I in;

    @PB_A(name = "출력")
    public O out;
}
