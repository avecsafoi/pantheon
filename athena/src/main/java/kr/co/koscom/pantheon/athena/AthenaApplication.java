package kr.co.koscom.pantheon.athena;

import kr.co.koscom.pantheon.athena.base.db.plugins.XMyBatisInitializer;
import kr.co.koscom.pantheon.athena.base.db.plugins.page.XMyBatisInterceptor;
import kr.co.koscom.pantheon.athena.base.io.data.TextXDataGenericHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
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

//    @Bean
//    public GlobalOperationCustomizer globalOperationCustomizer() {
//        return (Operation operation, HandlerMethod handlerMethod) -> {
//            String textPlain = org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
//            MediaType mt = new MediaType();
//            operation.getRequestBody().getContent().addMediaType(textPlain, mt);
//            ApiResponse x = operation.getResponses().get("200");
//            Content content = x.getContent();
//            if (content == null) x.setContent(content = new Content());
//            content.addMediaType(textPlain, mt);
//            return operation;
//        };
//    }

    @Bean
    @Description("MyBatis XML 쿼리 구문 앞에 주석으로 /* SQLID = mybatis_sql_id */ 추가하는 기능")
    public XMyBatisInitializer xMyBatisInitializer() {
        return new XMyBatisInitializer();
    }

    @EventListener(ApplicationReadyEvent.class)
    @Description("스프링실행(시작완료) 후 이벤트")
    public void initialize(ApplicationReadyEvent event) {
        /* xMyBatisInitializer().init(); */
    }
}
