package kr.co.koscom.olympus.pb.aps.sw.mm.q002;

import java.math.BigDecimal;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class Swmmq002Out extends PBObject {

	@PBA(name = "시작일")
	public String zSrtDt;

	@PBA(name = "종료일")
	public String zEndDt;

	@PBA(name = "이자율")
	public BigDecimal dIntRat;

	@PBA(name = "수수료율")
	public BigDecimal dCmsRat;
}
