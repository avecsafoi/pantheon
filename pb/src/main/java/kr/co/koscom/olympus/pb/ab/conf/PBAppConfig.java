package kr.co.koscom.olympus.pb.ab.conf;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.datasource.DataSourceManager;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.plugin.PBDataSourceShardingStrategy;
import kr.co.koscom.olympus.pb.ab.plugin.PBPageInterceptor;
import kr.co.koscom.olympus.pb.ab.plugin.PBTenantFactory;
import kr.co.koscom.olympus.pb.ab.plugin.PBTextDataHttpMessageConverter;
import kr.co.koscom.olympus.pb.apa.PBService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.event.EventListener;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class PBAppConfig {

    public static final Map<String, Object> PBServiceMap = new HashMap<>();

    @Resource
    private ApplicationContext ctx;

    @Bean
    @Description("송수신 전문을 파싱하는 기능")
    public PBTextDataHttpMessageConverter pbTextDataHttpMessageConverter() {
        return new PBTextDataHttpMessageConverter();
    }

    @Bean
    @Description("CPage 객체를 사용하는 쿼리구문에 연속조회 구문을 추가하는 기능")
    public PBPageInterceptor pbPageInterceptor() {
        return new PBPageInterceptor();
    }

    @Bean
    @Description("BP 커스텀 설정 -> application.properties 의 pb.* 참조")
    PBProperties pbProperties() {
        return new PBProperties();
    }


    @Bean
    @Description("테넌트 설정*")
    public PBTenantFactory pbTenantFactory() {
        return new PBTenantFactory();
    }

    @EventListener(ApplicationReadyEvent.class)
    @Description("스프링실행(시작완료) 후 이벤트")
    public void initialize(ApplicationReadyEvent event) {

        // 회원사별 구분컬럼 자동입력 처리
        setMultiTenancy();

        // 읽기/쓰기 DB 자동 구분 설정
        setDataSourceSharding();

        // PBServiceMap 만들기
        setPBServiceMap();
    }

    @Description("멀티 테넌시 설정")
    private void setMultiTenancy() {
        if (pbProperties().isMultiTenantSysEnable()) { // TODO 연결해제 설정
            // @Column(tenantId = true) 하지 않아도, 컬럼이름으로 자동인식 처리 전역 설정
            FlexGlobalConfig.getDefaultConfig().setTenantColumn("tenantId");
        }
    }

    @Description("읽기/쓰기 DB 자동 구분 설정")
    private void setDataSourceSharding() {
        DataSourceManager.setDataSourceShardingStrategy(new PBDataSourceShardingStrategy());
    }

    @Description("PBServiceMap 만들기")
    private void setPBServiceMap() {
        int cnt = ctx.getBeanDefinitionCount(), i = 0;
        System.out.printf("---- >> BEANS (%d) --------------------- %n", cnt);
        for (String n : ctx.getBeanDefinitionNames()) {

            Object o = ctx.getBean(n);

            if (o instanceof PBService) {
                PBServiceMap.put(n, o);
            }

            System.out.printf("[%5d] [ %s ]%n", ++i, n);
            for (Class<?> c = o.getClass(); c != null && c != Object.class; c = c.getSuperclass()) {
                if (Proxy.isProxyClass(c)) c = Proxy.getInvocationHandler(o).getClass();
                System.out.printf("        [ %s ]%n", c);
            }
            System.out.printf("%n");

        }
        System.out.printf("---- << BEANS (%d) --------------------- %n", cnt);
    }
}
