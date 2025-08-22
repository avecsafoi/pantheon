package kr.co.koscom.olympus.pb.test.text;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import static kr.co.koscom.olympus.pb.apa.PBCommon.*;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class TestSubVo extends PBObject {

    @PBA(name = "종목", scale = Z_ISU_NO)
    private String isu;

    @PBA(name = "수량", scale = Z_SHORT)
    private short qty;

    @PBA(name = "기타", scale = Z_REMARK)
    private String mark;
}
