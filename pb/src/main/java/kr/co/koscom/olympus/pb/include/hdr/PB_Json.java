package kr.co.koscom.olympus.pb.include.hdr;

import kr.co.koscom.olympus.pb.include.PB_A;
import kr.co.koscom.olympus.pb.include.PB_Object;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Description("JSON 송수신 객체")
public class PB_Json extends PB_Object {

    @PB_A(name = "공통 헤더")
    public PB_HdrCommon hdrCommon;

    @PB_A(name = "계정계 헤더")
    public PB_HdrAccount hdrAccount;

    @PB_A(name = "송수신 데이터")
    public Map<?, ?> data;
}
