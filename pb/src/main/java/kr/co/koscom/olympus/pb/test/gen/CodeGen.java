package kr.co.koscom.olympus.pb.test.gen;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.ColumnConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class CodeGen {

//    @Resource
//    private DataSource dataSource;

    public static void main(String[] args) throws Exception {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mariadb://127.0.0.1:3306/pb?characterEncoding=utf-8");
        dataSource.setUsername("pb");
        dataSource.setPassword("pb");

        CodeGen test = new CodeGen();
        test.generate(dataSource);
    }

    public static GlobalConfig createGlobalConfigUseStyle1() {
        // 구성 콘텐츠 생성
        GlobalConfig globalConfig = new GlobalConfig();

        // 루트 패키지 설정
        globalConfig.setBasePackage("kr.co.koscom.olympus.pb.auto");

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
        globalConfig.setTableDefGenerateEnable(true);

        // 개별 설정
//        ColumnConfig columnConfig = new ColumnConfig();
//        columnConfig.setColumnName("tenant_id");
//        columnConfig.setLarge(true);
//        columnConfig.setVersion(true);
//        globalConfig.setColumnConfig("tb_account", columnConfig);

        return globalConfig;
    }

    public static GlobalConfig createGlobalConfigUseStyle2() {
        // 구성 콘텐츠를 생성합니다.
        GlobalConfig globalConfig = new GlobalConfig();

        // 루트 패키지를 설정합니다.
        globalConfig.getPackageConfig().setBasePackage("com.test");

        // 테이블 접두사와 생성할 테이블을 설정합니다.
        // setGenerateTable을 구성하지 않으면 모든 테이블이 생성됩니다.
        globalConfig.getStrategyConfig()
                .setTablePrefix("tb_")
                .setGenerateTable("tb_account", "tb_account_session");

        // 엔티티 생성을 설정하고 Lombok을 활성화하세요
        globalConfig.enableEntity()
                .setWithLombok(true)
                .setJdkVersion(24);

        // 생성된 매퍼 설정
        globalConfig.enableMapper();

        // 열을 개별적으로 구성할 수 있습니다
        ColumnConfig columnConfig = new ColumnConfig();
        columnConfig.setColumnName("tenant_id");
        columnConfig.setLarge(true);
        columnConfig.setVersion(true);
        globalConfig.getStrategyConfig()
                .setColumnConfig("tb_account", columnConfig);

        return globalConfig;
    }

    void generate(DataSource dataSource) {
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();
        Generator generator = new Generator(dataSource, globalConfig);
        generator.generate();
    }
}