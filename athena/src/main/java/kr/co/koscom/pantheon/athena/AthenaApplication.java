package kr.co.koscom.pantheon.athena;

import kr.co.koscom.pantheon.athena.base.db.plugins.page.XMyBatisInterceptor;
import kr.co.koscom.pantheon.athena.base.io.TextXDataHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    public TextXDataHttpMessageConverter textXDataHttpMessageConverter() {
        return new TextXDataHttpMessageConverter();
    }
}
