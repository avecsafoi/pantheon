package kr.co.koscom.olympus.pb.on.bms;

import kr.co.koscom.olympus.pb.include.data.PBObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class SPOT_ORD_Q1 extends PBObject {

    public String ordDt;
    public String qty;
}
