package kr.co.koscom.olympus.pb.on.bms;

import kr.co.koscom.olympus.pb.include.PB_Object;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class SPOT_ORD_Q1 extends PB_Object {

    public String ordDt;
    public String qty;
}
