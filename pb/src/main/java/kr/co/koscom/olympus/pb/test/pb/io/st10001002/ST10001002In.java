package kr.co.koscom.olympus.pb.test.pb.io.st10001002;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBPageData;
import kr.co.koscom.olympus.pb.test.pb.io.OrdQi;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import static kr.co.koscom.olympus.pb.include.PBCommon.Z_ACNT_NO;
import static kr.co.koscom.olympus.pb.include.PBCommon.Z_ISU_NO;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class ST10001002In extends PBObject implements PBPageData<PBCPage> {

    @PBA(name = "입력객체")
    private OrdQi ordQi;

    @PBA(name = "페이지")
    private PBCPage page;
}
