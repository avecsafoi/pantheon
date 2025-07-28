package kr.co.koscom.pantheon.athena;

import kr.co.koscom.pantheon.athena.base.io.TextKDataHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AthenaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AthenaApplication.class, args);
    }

    @Bean
    public KMyBatisInterceptor kMyBatisInterceptor() {
        return new KMyBatisInterceptor();
    }

    @Bean
    public TextKDataHttpMessageConverter textKDataHttpMessageConverter() {
        return new TextKDataHttpMessageConverter();
    }
}
