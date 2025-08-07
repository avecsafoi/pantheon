package kr.co.koscom.olympus.pb.include.hdr;

import kr.co.koscom.olympus.pb.include.PB_A;
import kr.co.koscom.olympus.pb.include.PB_Object;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Description("가변 헤더")
public class PB_HdrAccVarHtsUser extends PB_Object {

    @PB_A(name = "처리제한", scale = 1)
    public String aTrxRestrcTp;

    @PB_A(name = "영업점IP 여부", scale = 1)
    public String aBrnIpYn;

    @PB_A(name = "서비스인덱스번호", scale = 4)
    public int aSvcIdxNo;

    @PB_A(name = "보고서 출력구분", scale = 1)
    public String aRptOtpTp;

    @PB_A(name = "미사용", scale = 9)
    public String aFiller;

}
