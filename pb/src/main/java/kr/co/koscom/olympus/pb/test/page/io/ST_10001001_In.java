package kr.co.koscom.olympus.pb.test.page.io;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBPageData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import static kr.co.koscom.olympus.pb.include.PBCommon.Z_ACNT_NO;
import static kr.co.koscom.olympus.pb.include.PBCommon.Z_ISU_NO;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class ST_10001001_In extends PBObject implements PBPageData<PBCPage> {

    @PBA(name = "계좌번호", scale = Z_ACNT_NO)
    private String acno;

    @PBA(name = "종목정보", scale = Z_ISU_NO)
    private String isu;

    @PBA(name = "페이지")
    private PBCPage page;
}
