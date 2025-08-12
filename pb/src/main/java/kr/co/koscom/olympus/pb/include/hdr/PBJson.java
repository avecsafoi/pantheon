package kr.co.koscom.olympus.pb.include.hdr;

import kr.co.koscom.olympus.pb.include.PBA;
import kr.co.koscom.olympus.pb.include.data.PBData;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

import java.util.Map;

@Accessors(chain = true)
@Data
@Description("JSON 송수신 객체")
public class PBJson implements PBData {

    @PBA(name = "공통 헤더")
    public PBHdrCommon hdrCommon;

    @PBA(name = "계정계 헤더")
    public PBHdrAccount hdrAccount;

    @PBA(name = "송수신 데이터")
    public Map<?, ?> data;
}
