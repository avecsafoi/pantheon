package kr.co.koscom.olympus.pb.test.vue;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ap.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.ap.mapper.OnTblSpotOrdMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pb/vue")
public class VueTestController {

    @Resource
    private OnTblSpotOrdMapper mapper;

    @PostMapping("list")
    public @ResponseBody Page<OnTblSpotOrd> list(@RequestBody Page<OnTblSpotOrd> pg) throws Throwable {
        QueryWrapper qw = new QueryWrapper();
        return mapper.paginate(pg, qw);
    }

    @PostMapping("order")
    public @ResponseBody Page<OnTblSpotOrd> order(@RequestBody Page<OnTblSpotOrd> pg) throws Throwable {

        int[] i = {0};
        pg.getRecords().forEach(a -> {
            i[0] += mapper.insert(a);
        });
        return pg;
    }
}
