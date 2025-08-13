package kr.co.koscom.olympus.pb.test.text;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class TestSubVo extends PBObject {

    @PBA(name = "종목", scale = 12)
    private String isu;

    @PBA(name = "수량", scale = 5)
    private short qty;

    @PBA(name = "기타", scale = 20)
    private String mark;
}
