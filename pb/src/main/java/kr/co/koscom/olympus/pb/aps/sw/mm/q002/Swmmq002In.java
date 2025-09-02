package kr.co.koscom.olympus.pb.aps.sw.mm.q002;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class Swmmq002In extends PBObject {

	@PBA(name = "종목번호")
	public String zIsuNo;

	@PBA(name = "시작일")
	public String zQrySrtDt;

	@PBA(name = "종료일")
	public String zQryEndDt;

	@PBA(name = "페이지")
	public String page;
}
