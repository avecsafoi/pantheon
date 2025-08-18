package kr.co.koscom.olympus.pb.test.pb;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.db.mapper.OnTblSpotOrdMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/pb/page")
public class PBPageController {

    @Resource
    private OnTblSpotOrdMapper mapper;

    @PostMapping(value = "page01")
    public @ResponseBody List<OnTblSpotOrd> page01(@RequestBody io.OrdQi ordQi) {
        return mapper.page01(ordQi);
    }

    @PostMapping(value = "page02")
    public @ResponseBody List<OnTblSpotOrd> page02(@RequestBody io.OrdQi ordQi) {
        return mapper.page02(ordQi);
    }

    @PostMapping(value = "page03")
    public @ResponseBody List<OnTblSpotOrd> page03(@RequestBody HashMap<String, io.OrdQi> m) {
        io.OrdQi q1 = m.get("q1");
        io.OrdQi q2 = m.get("q2");
        return mapper.page03(q1, q2);
    }
}
