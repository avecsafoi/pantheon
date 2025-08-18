package kr.co.koscom.olympus.pb.test.pb.io.st10001001;

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
public class ST10001001In extends PBObject implements PBPageData<PBCPage> {

    @PBA(name = "계좌번호", scale = Z_ACNT_NO)
    private String acntNo;

    @PBA(name = "종목코드", scale = Z_ISU_NO)
    private String isuNo;

    @PBA(name = "페이지")
    private PBCPage page;
}
