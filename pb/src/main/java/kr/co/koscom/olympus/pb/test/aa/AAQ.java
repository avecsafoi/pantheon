package kr.co.koscom.olympus.pb.test.aa;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class AAQ extends PBObject {

    @PBA(name = "아이디", scale = 20)
    private String id;

    @PBA(name = "페이지")
    private PBCPage page;
}
