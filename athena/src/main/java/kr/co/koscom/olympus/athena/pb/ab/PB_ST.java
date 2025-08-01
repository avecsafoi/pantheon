package kr.co.koscom.olympus.athena.pb.ab;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public abstract class PB_ST<I extends PB_In_ST, O extends PB_Out_ST> extends PB_DataObject {

    public final I in;
    public final O out;

    CharSequence x;
    String y;

    public PB_ST(I in, O out) {
        this.in = in;
        this.out = out;
    }
}
