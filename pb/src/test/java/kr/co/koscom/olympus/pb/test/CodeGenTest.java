package kr.co.koscom.olympus.pb.test;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.db.mapper.PBMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
public class CodeGenTest {

    @Resource
    DataSource dataSource;

    public GlobalConfig createGlobalConfigUseStyle1() {
        // 구성 콘텐츠 생성
        GlobalConfig globalConfig = new GlobalConfig();

        // 루트 패키지 설정
        globalConfig.setSourceDir("../auto");
        globalConfig.setBasePackage("kr.co.koscom.olympus.pb.ap");

        // 테이블 접두사 및 생성대상 테이블 (대상 테이블을 설정하지 않으면 모든 테이블)
        // globalConfig.setTablePrefix("tb_");
        // globalConfig.setGenerateTable("tb_account", "tb_account_session");

        // 엔티티 생성, Lombok 활성화
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);
        // JDK 버전설정
        globalConfig.setEntityJdkVersion(24);

        // mapper 생성 설정
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.setMapperXmlGenerateEnable(true);
        globalConfig.setMapperSuperClass(PBMapper.class);

        // 기타
        globalConfig.setTableDefGenerateEnable(true);
        globalConfig.setControllerGenerateEnable(true);

        globalConfig.setServiceGenerateEnable(true);
        globalConfig.setServiceImplGenerateEnable(true);

        // 개별 설정
//        ColumnConfig columnConfig = new ColumnConfig();
//        columnConfig.setColumnName("tenant_id");
//        columnConfig.setLarge(true);
//        columnConfig.setVersion(true);
//        globalConfig.setColumnConfig("tb_account", columnConfig);

        return globalConfig;
    }

    @Test
    void test_main() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mariadb://127.0.0.1:3306/pb?characterEncoding=utf-8");
        dataSource.setUsername("pb");
        dataSource.setPassword("pb");

        CodeGenTest test = new CodeGenTest();
        test.generate(dataSource);
    }

    void generate(DataSource dataSource) {
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();
        Generator generator = new Generator(dataSource, globalConfig);
        generator.generate();
    }
}