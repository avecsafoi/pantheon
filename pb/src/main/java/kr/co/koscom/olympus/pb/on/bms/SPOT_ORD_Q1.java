package kr.co.koscom.olympus.pb.on.bms;

import kr.co.koscom.olympus.pb.base.data.PBData;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class SPOT_ORD_Q1 implements PBData {

    public String ordDt;
    public String qty;
}
