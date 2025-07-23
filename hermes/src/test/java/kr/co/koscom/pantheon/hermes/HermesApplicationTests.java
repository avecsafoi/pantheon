package kr.co.koscom.pantheon.hermes;

import com.baomidou.mybatisplus.core.mapper.HBaseMapper;
import com.baomidou.mybatisplus.extension.plugins.inner.HPaginationInnerInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HermesApplicationTests {

    @Test
    void contextLoads() {
        HPaginationInnerInterceptor x1;
        HBaseMapper x2;
    }

}
