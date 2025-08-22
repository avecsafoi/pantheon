package kr.co.koscom.olympus.pb.test.flex;

import com.mybatisflex.annotation.UseDataSource;
import com.mybatisflex.core.datasource.DataSourceKey;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ap.entity.Test001;
import kr.co.koscom.olympus.pb.ap.mapper.Test001Mapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller("/pb/flex")
class FlexController {

    @Resource
    private Test001Mapper mapper;

    @PostMapping("insertOrupdate")
    public @ResponseBody Test001 insert1(@RequestBody Test001 o) {

        Random random = new Random();
        RandomStringUtils rs = RandomStringUtils.insecure();

        if (o.getNo1() == null) o.setNo1(random.nextLong());
        if (o.getNo2() == null) o.setNo2(random.nextLong());
        if (StringUtils.isBlank(o.getId1())) o.setId1(rs.nextAlphabetic(10));

        o.setNo3(null);
        o.setName1("First");
        o.setName2("IgnoreNull=false");
        mapper.insertOrUpdate(o);

        // PK 값 초기화
        o.setNo3(null);
        o.setName1("Second");
        o.setName2("IgnoreNull=true");
        mapper.insertOrUpdate(o, true); // null 필드 제외

        o.setName1("Third");
        o.setName2("IgnoreNull=false");
        o.setName3(rs.nextAlphanumeric(10));
        mapper.insertOrUpdate(o); // null 필드 포함

        return o;
    }

    @PostMapping("pbx1")
    public @ResponseBody Test001 pbx1(@RequestBody Test001 o) {
        Long n = o.getNo1();
        int i = Math.floorMod(n + 1, 2) + 1;
        String nm = "pb" + i;
        try {
            DataSourceKey.use(nm);
            o.setName2(nm);
            int r = mapper.insertOrUpdate(o);
            System.out.println(r);
        } finally {
            DataSourceKey.clear();
        }
        return o;
    }

    @UseDataSource("pb4")
    @PostMapping("pbx2")
    public @ResponseBody Test001 pbx2(@RequestBody Test001 o) {
        Long n = o.getNo1();
        int i = Math.floorMod(n + 1, 2) + 1;
        String nm = "pb" + i;
        try {
            DataSourceKey.use(nm);
            o.setName2(nm);
            int r = mapper.insertOrUpdate(o);
            System.out.println(r);
        } finally {
            DataSourceKey.clear();
        }
        return o;
    }

    @UseDataSource("pb3")
    @PostMapping("pb3")
    public @ResponseBody Test001 pb3(@RequestBody Test001 o) {
        o.setName2(DataSourceKey.get());
        int r = mapper.insertOrUpdate(o);
        System.out.println(r);
        return o;
    }

    @UseDataSource("pb4")
    @PostMapping("pb4")
    public @ResponseBody Test001 pb4(@RequestBody Test001 o) {
        o.setName2(DataSourceKey.get());
        int r = mapper.insertOrUpdate(o);
        System.out.println(r);
        return o;
    }
}
