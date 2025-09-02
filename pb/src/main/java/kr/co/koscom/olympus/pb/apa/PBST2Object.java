package kr.co.koscom.olympus.pb.apa;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.apa.hdr.PBHdrAccount;
import kr.co.koscom.olympus.pb.apa.hdr.PBHdrCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class PBST2Object extends PBObject implements PBST2 {

	@PBA(name = "공통 헤더")
	protected PBHdrCommon hdrCommon;

	@PBA(name = "계정계 헤더")
	protected PBHdrAccount hdrAccount;

}
