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
@Description("공통헤더")
public class PB_HdrCommon extends PB_Object {

    @PB_A(name = "전문길이", scale = 6)
    public int aTgLen;

    @PB_A(name = "전문구분", scale = 1)
    public String aTgTp;

    @PB_A(name = "압축구분", scale = 1)
    public String aCmprstype;

    @PB_A(name = "암호화구분", scale = 1)
    public String aEncrpType;

    @PB_A(name = "계정계 Header Length", scale = 3)
    public int aDataOfst;

}
