package kr.co.koscom.pantheon.athena;

import kr.co.koscom.pantheon.athena.base.db.plugins.XMyBatisInitializer;
import kr.co.koscom.pantheon.athena.base.db.plugins.page.XMyBatisInterceptor;
import kr.co.koscom.pantheon.athena.base.io.TextXDataGenericHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AthenaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AthenaApplication.class, args);
    }

    @Bean
    public XMyBatisInterceptor xMyBatisInterceptor() {
        return new XMyBatisInterceptor();
    }

    @Bean
    public TextXDataGenericHttpMessageConverter textXDataHttpMessageConverter() {
        // return new TextXDataHttpMessageConverter(); // 기본객체만 처리가능 (GenericType 객체는 다른 컨버터로 패스)
        return new TextXDataGenericHttpMessageConverter(); // GenericType IO 처리가능 예) @ResponseBody List<User> users(@RequestBody List<User>);
    }

    @Bean
    public XMyBatisInitializer xMyBatisInitializer() {
        return new XMyBatisInitializer();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialize(ApplicationReadyEvent event) {
        /* xMyBatisInitializer().init(); */
    }
}
