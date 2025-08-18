package kr.co.koscom.olympus.pb.test.pb;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.on.bms.OnbSpotOrdSP_BSMM;
import kr.co.koscom.olympus.pb.on.bms.SPOT_ORD_TRX_SP_ST;
import org.springframework.web.bind.annotation.*;

import static kr.co.koscom.olympus.pb.include.PBCommon.SUCCESS;

@RestController
@RequestMapping("/pb/st")
public class PBOrdStController {

    @Resource
    private OnbSpotOrdSP_BSMM ordSPBsmm;

    @PostMapping("ord")
    public @ResponseBody SPOT_ORD_TRX_SP_ST ord(@RequestBody SPOT_ORD_TRX_SP_ST st) throws Throwable {
        int n = ordSPBsmm.process(st);
        if (n != SUCCESS) {
            throw new RuntimeException(st.getHdrAccount().getAOutMsgTp());
        }
        return st;
    }
}
