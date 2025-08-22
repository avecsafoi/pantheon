package kr.co.koscom.olympus.pb.aps.on.bms;


import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ap.mapper.OnTblSpotOrdMapper;
import kr.co.koscom.olympus.pb.apa.PBService;
import org.springframework.stereotype.Service;

@Service("10001000")
public class OnbSpotOrdSP_BSMM implements PBService<SPOT_ORD_TRX_SP_ST> {

    @Resource
    private OnTblSpotOrdMapper mapper;

    @Override
    public void process(SPOT_ORD_TRX_SP_ST st) {
        mapper.ON_FN_SPOT_ORD_MAIN(st);
    }
}
