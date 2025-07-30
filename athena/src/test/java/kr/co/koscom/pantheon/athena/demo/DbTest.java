package kr.co.koscom.pantheon.athena.demo;

import jakarta.annotation.Resource;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DbTest {

    @Resource
    SqlSession ss;

    @Test
    void queryList() {
        for (MappedStatement ms : ss.getConfiguration().getMappedStatements()) {
            ParameterMap pm = ms.getParameterMap();
            SqlSource sqlSource = ms.getSqlSource();
            System.out.printf("%s = [%s]%n", ms.getId(), ms.getResource());
        }
        System.exit(0);
    }
}
