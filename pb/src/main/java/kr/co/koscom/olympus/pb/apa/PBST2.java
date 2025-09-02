package kr.co.koscom.olympus.pb.apa;

import kr.co.koscom.olympus.pb.apa.hdr.PBHdrAccount;
import kr.co.koscom.olympus.pb.apa.hdr.PBHdrCommon;

public interface PBST2 {

	PBHdrCommon getHdrCommon();

	<X extends PBST2> X setHdrCommon(PBHdrCommon hdrCommon);

	PBHdrAccount getHdrAccount();

	<X extends PBST2> X setHdrAccount(PBHdrAccount hdrAccount);
}
