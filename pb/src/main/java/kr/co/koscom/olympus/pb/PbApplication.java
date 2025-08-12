package kr.co.koscom.olympus.pb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PbApplication {

    public static void main(String[] args) {
        SpringApplication.run(PbApplication.class, args);
    }

//    @Bean
//    @Description("송수신 전문을 파싱하는 기능")
//    public PBTextDataHttpMessageConverter pbTextDataHttpMessageConverter() {
//        return new PBTextDataHttpMessageConverter();
//    }
//
//    @Bean
//    @Description("쿼리구문에 연속조회 구문을 추가하는 기능")
//    public PBMyBatisInterceptor pbMyBatisInterceptor() {
//        return new PBMyBatisInterceptor();
//    }
}
