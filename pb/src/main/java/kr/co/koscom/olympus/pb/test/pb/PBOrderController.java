package kr.co.koscom.olympus.pb.test.pb;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.on.bms.OnbSpotOrdSP_BSMM;
import kr.co.koscom.olympus.pb.on.bms.SPOT_ORD_TRX_SP_ST;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static kr.co.koscom.olympus.pb.include.PBCommon.SUCCESS;

@Controller
@RequestMapping("/home")
public class PBOrderController {

    @Resource
    private OnbSpotOrdSP_BSMM ordSPBsmm;

    @PostMapping("/List")
    public @ResponseBody SPOT_ORD_TRX_SP_ST list(@RequestBody SPOT_ORD_TRX_SP_ST st) throws Throwable {
        int n = ordSPBsmm.process(st);
        if (n != SUCCESS) {
            throw new RuntimeException(st.getHdrAccount().getAOutMsgTp());
        }
        return st;
    }
}
