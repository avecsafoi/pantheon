package kr.co.koscom.olympus.pb.on.bms;


import org.springframework.stereotype.Service;

import static kr.co.koscom.olympus.pb.on.bms.PBCommon.SUCCESS;

@Service("PB_SID 10001000")
public class OnbSpotOrdSP_BSMM implements PBService<SPOT_ORD_TRX_SP_ST> {

    @Override
    public int call(SPOT_ORD_TRX_SP_ST st) {
        return SUCCESS;
    }
}
