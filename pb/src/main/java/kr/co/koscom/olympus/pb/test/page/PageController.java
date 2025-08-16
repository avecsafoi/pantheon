package kr.co.koscom.olympus.pb.test.page;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.db.mapper.OnTblSpotOrdMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pb/page")
public class PageController {

    @Resource
    private OnTblSpotOrdMapper mapper;

    @RequestMapping(value = "pg1", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Page<OnTblSpotOrd> pg1(@RequestBody Page<OnTblSpotOrd> page) {
        QueryWrapper qw = new QueryWrapper();
        return mapper.paginate(page, qw);
    }

    @RequestMapping(value = "pg2", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Page<OnTblSpotOrd> pg2(@RequestBody Page<OnTblSpotOrd> page) {
        QueryWrapper qw = new QueryWrapper();
        return mapper.paginate(page, qw);
    }

    @RequestMapping(value = "pg3", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Page<OnTblSpotOrd> pg3(@RequestBody Page<OnTblSpotOrd> page) {
        QueryWrapper qw = new QueryWrapper();
        return mapper.paginate(page, qw);
    }
}
