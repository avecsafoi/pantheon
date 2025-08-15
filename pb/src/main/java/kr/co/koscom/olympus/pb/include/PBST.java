package kr.co.koscom.olympus.pb.include;

import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.include.hdr.PBHdrAccount;

public interface PBST<I, O> extends PBData {

    PBHdrAccount getHdrCommon();

    PBST<I, O> setHdrCommon(PBHdrAccount hdrCommon);

    PBHdrAccount getHdrAccount();

    PBST<I, O> setHdrAccount(PBHdrAccount hdrAccount);

    I getIn();

    PBST<I, O> setIn(I in);

    O getOut();

    PBST<I, O> setOut(O out);
}
