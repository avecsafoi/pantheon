package kr.co.koscom.olympus.pb.aps.sw.mm.q002;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBCheck;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class Swmmq002In extends PBObject {

	@PBA(name = "종목번호", check = { PBCheck.ISU_NO })
	public String zIsuNo;

	@PBA(name = "시작일", check = { PBCheck.DATE })
	public String zSrtDt;

	@PBA(name = "종료일")
	public String zEndDt;

	@PBA(name = "페이지")
	public PBCPage page;
}
