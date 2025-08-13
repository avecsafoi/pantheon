package kr.co.koscom.olympus.pb.test.flex;

import com.mybatisflex.annotation.UseDataSource;
import com.mybatisflex.core.datasource.DataSourceKey;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.db.entity.Test001;
import kr.co.koscom.olympus.pb.db.mapper.Test001Mapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

@Controller("/pb/flex")
class FlexController {

    @Resource
    private Test001Mapper mapper;

    @PostMapping("insertOrupdate")
    public Test001 insert1(Test001 o) {

        Random random = new Random();
        RandomStringUtils rs = RandomStringUtils.insecure();

        if (o.getNo1() == null) o.setNo1(random.nextLong());
        if (o.getNo2() == null) o.setNo2(random.nextLong());
        if (StringUtils.isBlank(o.getId1())) o.setId1(rs.nextAlphabetic(10));

        o.setId3(null);
        mapper.insertOrUpdate(o);

        // PK 값 초기화
        o.setNo3(null);
        mapper.insertOrUpdate(o, true); // null 값인 필드도 인서트 (DB 컬럼, default 값 무시)

        o.setName3(rs.nextAlphanumeric(10));
        mapper.insertOrUpdate(o, true); // null 값인 필드도 업데이트

        return o;
    }

    public Test001 db(Test001 o) {
        Long n = o.getNo1();
        int i = Math.floorMod(n, 2) + 1;
        String nm = "ds" + i;
        try {
            DataSourceKey.use(nm);
            o.setName2(nm);
            mapper.insertOrUpdate(o);
        } catch (Exception e) {
            throw e;
        } finally {
            DataSourceKey.clear();
        }
        return o;
    }

    @UseDataSource("ds4")
    @PostMapping("db4")
    public Test001 db4(Test001 o) {
        o.setName2(DataSourceKey.get());
        mapper.insertOrUpdate(o);
        return o;
    }

    @UseDataSource("ds5")
    @PostMapping("ds5")
    public Test001 db5(Test001 o) {
        o.setName2(DataSourceKey.get());
        mapper.insertOrUpdate(o);
        return o;
    }
}
