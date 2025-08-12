package kr.co.koscom.olympus.pb.include.hdr;

import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

@Accessors(chain = true)
@Data
@Description("가변 헤더")
public class PBHdrAccVarHtsUser implements PBData {

    @PBA(name = "처리제한", scale = 1)
    public String aTrxRestrcTp;

    @PBA(name = "영업점IP 여부", scale = 1)
    public String aBrnIpYn;

    @PBA(name = "서비스인덱스번호", scale = 4)
    public int aSvcIdxNo;

    @PBA(name = "보고서 출력구분", scale = 1)
    public String aRptOtpTp;

    @PBA(name = "미사용", scale = 9)
    public String aFiller;

}
