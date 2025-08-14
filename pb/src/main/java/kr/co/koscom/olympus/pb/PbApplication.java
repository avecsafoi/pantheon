package kr.co.koscom.olympus.pb;

import com.mybatisflex.core.FlexGlobalConfig;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.db.plugin.PBDataSourceShardingStrategy;
import kr.co.koscom.olympus.pb.ab.db.plugin.PBMyBatisInterceptor;
import kr.co.koscom.olympus.pb.ab.db.plugin.PBTextDataHttpMessageConverter;
import kr.co.koscom.olympus.pb.include.PBService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.context.event.EventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    @Description("CPage 객체를 사용하는 쿼리구문에 연속조회 구문을 추가하는 기능")
    public PBMyBatisInterceptor pbMyBatisInterceptor() {
        return new PBMyBatisInterceptor();
    }

    @Bean
    @Description("읽기/쓰기 DB 자동 구분 설정")
    public PBDataSourceShardingStrategy pbDataSourceShardingStrategy() {
        return new PBDataSourceShardingStrategy();
    }

    @EventListener(ApplicationReadyEvent.class)
    @Description("스프링실행(시작완료) 후 이벤트")
    public void initialize(ApplicationReadyEvent event) {
        // @Column(tenantId = true) 하지 않아도, 컬럼이름으로 자동인식 처리 전역 설정
        // tenant 설정은 다른 db 선택 설정을 무시하고 최우선 적용됨
        FlexGlobalConfig.getDefaultConfig().setTenantColumn("tenant_id");

        // PBServiceMap 만들기
        buildPBServiceMap();
    }

    public static final Map<String, Object> PBServiceMap = new HashMap<>();

    @Resource
    private ApplicationContext ctx;

    private void buildPBServiceMap() {
        Arrays.stream(ctx.getBeanNamesForType(PBService.class)).forEach(n -> PBServiceMap.put(n, ctx.getBean(n)));
    }
}
