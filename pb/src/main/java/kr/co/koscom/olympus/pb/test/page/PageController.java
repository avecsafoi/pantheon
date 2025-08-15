package kr.co.koscom.olympus.pb.test.page;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.db.mapper.OnTblSpotOrdMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pb/page")
public class PageController {

    @Resource
    private OnTblSpotOrdMapper mapper;

    @RequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Page<OnTblSpotOrd> page(@RequestBody Page<OnTblSpotOrd> page) {
        QueryWrapper qw = new QueryWrapper();
        return mapper.paginate(page, qw);
    }
}
