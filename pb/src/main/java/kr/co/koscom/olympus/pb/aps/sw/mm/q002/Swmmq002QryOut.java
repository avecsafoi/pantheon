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
public class Swmmq002QryOut extends PBObject {

	@PBA(name = "시작일")
	public String srtDt;

	@PBA(name = "종료일")
	public String endDt;

	@PBA(name = "이자율")
	public BigDecimal ittIntRat;

	@PBA(name = "계좌이자율")
	public BigDecimal acntIntRat;

	@PBA(name = "회원사번호")
	public BigDecimal firmNo;

	@PBA(name = "수수료율")
	public BigDecimal dCmsRat;
}
