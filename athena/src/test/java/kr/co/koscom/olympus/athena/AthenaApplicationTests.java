package kr.co.koscom.olympus.athena;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.athena.base.XOrder;
import kr.co.koscom.olympus.athena.base.XPage;
import kr.co.koscom.olympus.athena.demo.mapper.DemoMapper;
import kr.co.koscom.olympus.athena.demo.model.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AthenaApplicationTests {

    @Resource
    private DemoMapper sysUserMapper;

    @Test
    void contextLoads() {

    }

    @Test
    void queryTest() {
        SysUser su = new SysUser();
        XPage pg = new XPage();
        List<SysUser> sul = sysUserMapper.selectPage(su, pg);
    }

    @Test
    void contTest() {
        SysUser su = new SysUser();
        XPage pg = new XPage();
        pg.getOrders().add(new XOrder("age", true, -1));
        pg.getOrders().add(new XOrder("name", true, "~"));
        pg.getOrders().add(new XOrder("email", true, "~"));
        pg.getOrders().add(new XOrder("id", true, "~"));
        pg.setFirst(false);

        ///DataSourceKey.use("ds2");
        List<SysUser> sul = sysUserMapper.selectContPage(su, pg);
        int i = 0;
        for (SysUser s : sul) {
            System.out.printf("[%4d %s]%n", i++, s);
        }
        ///DataSourceKey.clear();
    }
}
