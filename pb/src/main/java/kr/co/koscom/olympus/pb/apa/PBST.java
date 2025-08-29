package kr.co.koscom.olympus.pb.apa;

import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.apa.hdr.PBHdrAccount;
import kr.co.koscom.olympus.pb.apa.hdr.PBHdrCommon;

public interface PBST<I, O> extends PBData {

	PBHdrCommon getHdrCommon();

	PBST<I, O> setHdrCommon(PBHdrCommon hdrCommon);

	PBHdrAccount getHdrAccount();

	PBST<I, O> setHdrAccount(PBHdrAccount hdrAccount);

	I getIn();

	PBST<I, O> setIn(I in);

	O getOut();

	PBST<I, O> setOut(O out);
}
