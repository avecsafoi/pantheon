package kr.co.koscom.pantheon.athena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AthenaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AthenaApplication.class, args);
    }

    @Bean
    public AthenaMyBatisInterceptor myBatisInterceptor_For_Executor() {
        return new AthenaMyBatisInterceptor();
    }
}
