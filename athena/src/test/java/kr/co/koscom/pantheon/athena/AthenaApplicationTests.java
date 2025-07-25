package kr.co.koscom.pantheon.athena;

import jakarta.annotation.Resource;
import kr.co.koscom.pantheon.athena.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AthenaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private SysUserMapper sysUserMapper;

    @Test
    void queryTest() {
        SysUser su = new SysUser();
        KPage pg = new KPage();
        List<SysUser> sul = sysUserMapper.selectPage(su, pg);
    }

    @Test
    void contTest() {
        SysUser su = new SysUser();
        KPage pg = new KPage();
        pg.getOrders().add(new KOrder("age", true, -1));
        pg.getOrders().add(new KOrder("name", true, "~"));
        pg.getOrders().add(new KOrder("email", true, "~"));
        pg.getOrders().add(new KOrder("id", true, "~"));
        pg.setFirst(false);
        List<SysUser> sul = sysUserMapper.selectContPage(su, pg);
    }
}
