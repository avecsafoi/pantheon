package kr.co.koscom.olympus.pb.test.pb;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.aps.on.bms.OnbSpotOrdSP_BSMM;
import kr.co.koscom.olympus.pb.aps.on.bms.SPOT_ORD_TRX_SP_ST;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pb/st")
public class PBOrdStController {

    @Resource
    private OnbSpotOrdSP_BSMM ordSPBsmm;

    @PostMapping("ord")
    public @ResponseBody SPOT_ORD_TRX_SP_ST ord(@RequestBody SPOT_ORD_TRX_SP_ST st) throws Throwable {
        ordSPBsmm.process(st);
        return st;
    }
}
