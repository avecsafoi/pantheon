package kr.co.koscom.olympus.pb.test.vue;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.include.table.ON_TBL_SPOT_ORD;
import kr.co.koscom.olympus.pb.include.table.ON_TBL_SPOT_ORD_Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/pb/vue/")
public class VueTestController {

    @Resource
    private ON_TBL_SPOT_ORD_Mapper mapper;

    @PostMapping("vList")
    public @ResponseBody Page<ON_TBL_SPOT_ORD> list(@RequestBody Page<ON_TBL_SPOT_ORD> pg) throws Throwable {
        QueryWrapper qw = new QueryWrapper();
        return mapper.paginate(pg, qw);
    }

    @PostMapping("vOrder")
    public @ResponseBody Page<ON_TBL_SPOT_ORD> order(@RequestBody Page<ON_TBL_SPOT_ORD> pg) throws Throwable {

        int[] i = {0};
        pg.getRecords().forEach(a -> {
            i[0] += mapper.insert(a);
        });
        return pg;
    }
}
