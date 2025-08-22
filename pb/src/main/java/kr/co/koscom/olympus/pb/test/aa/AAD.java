package kr.co.koscom.olympus.pb.test.aa;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class AAD extends PBObject {
    private int no1;
//    private double no2;
//    private BigDecimal no3;
//    private String nm1;
//    private String nm2;
    private String nm3;
}
