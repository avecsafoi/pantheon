package kr.co.koscom.olympus.pb.include.hdr;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Description("JSON 송수신 객체")
public class PBJson extends PBObject {

    @PBA(name = "공통 헤더")
    public PBHdrCommon hdrCommon;

    @PBA(name = "계정계 헤더")
    public PBHdrAccount hdrAccount;

    @PBA(name = "송수신 데이터")
    public Map<?, ?> data;
}
