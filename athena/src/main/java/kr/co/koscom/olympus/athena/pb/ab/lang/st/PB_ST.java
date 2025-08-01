package kr.co.koscom.olympus.athena.pb.ab.lang.st;

import kr.co.koscom.olympus.athena.pb.ab.lang.PB_DataObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(fluent = true)
public abstract class PB_ST<I extends PB_IN_ST, O extends PB_OUT_ST> extends PB_DataObject {

    public I in;
    public O out;

    public PB_ST(I in, O out) {
        this.in = in;
        this.out = out;
    }
}
