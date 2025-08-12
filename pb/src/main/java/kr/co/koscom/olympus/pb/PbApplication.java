package kr.co.koscom.olympus.pb;

import kr.co.koscom.olympus.pb.ab.data.converter.PBTextDataHttpMessageConverter;
import kr.co.koscom.olympus.pb.ab.db.page.PBMyBatisInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;

@SpringBootApplication
public class PbApplication {

    public static void main(String[] args) {
        SpringApplication.run(PbApplication.class, args);
    }

    @Bean
    @Description("송수신 전문을 파싱하는 기능")
    public PBTextDataHttpMessageConverter pbTextDataHttpMessageConverter() {
        return new PBTextDataHttpMessageConverter();
    }

    @Bean
    @Description("쿼리구문에 연속조회 구문을 추가하는 기능")
    public PBMyBatisInterceptor pbMyBatisInterceptor() {
        return new PBMyBatisInterceptor();
    }
}
