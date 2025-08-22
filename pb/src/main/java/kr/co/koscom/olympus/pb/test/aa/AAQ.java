package kr.co.koscom.olympus.pb.test.aa;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBLock;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class AAQ extends PBObject {

    @PBA(name = "아이디1", scale = 20)
    private String nm1;

    @PBA(name = "페이지")
    private PBCPage page;

    @PBA(name = "웨이트")
    private PBLock lock;
}
