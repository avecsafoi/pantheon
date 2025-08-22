package kr.co.koscom.olympus.pb.apa.hdr;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Description("공통헤더")
public class PBHdrCommon extends PBObject {

    @PBA(name = "전문길이", scale = 6)
    public int tgLen;

    @PBA(name = "전문구분", scale = 1)
    public String tgTp;

    @PBA(name = "압축구분", scale = 1)
    public String cmprstype;

    @PBA(name = "암호화구분", scale = 1)
    public String encrpType;

    @PBA(name = "계정계 Header Length", scale = 3)
    public int dataOfst;

}
