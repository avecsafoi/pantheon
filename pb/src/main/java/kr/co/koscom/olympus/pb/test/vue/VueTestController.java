package kr.co.koscom.olympus.pb.test.vue;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.db.mapper.OnTblSpotOrdMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/pb/vue/")
public class VueTestController {

    @Resource
    private OnTblSpotOrdMapper mapper;

    @PostMapping("vList")
    public @ResponseBody Page<OnTblSpotOrd> list(@RequestBody Page<OnTblSpotOrd> pg) throws Throwable {
        QueryWrapper qw = new QueryWrapper();
        return mapper.paginate(pg, qw);
    }

    @PostMapping("vOrder")
    public @ResponseBody Page<OnTblSpotOrd> order(@RequestBody Page<OnTblSpotOrd> pg) throws Throwable {

        int[] i = {0};
        pg.getRecords().forEach(a -> {
            i[0] += mapper.insert(a);
        });
        return pg;
    }
}
