package kr.co.koscom.pantheon.athena.base.db;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.scripting.xmltags.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class XMyBatisInitializer {

    @Resource
    private SqlSession sqlSession;

    public void init() {
        try {
            for (MappedStatement ms : sqlSession.getConfiguration().getMappedStatements())
                setSqlId(ms.getSqlSource(), ms.getId());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private void setSqlId(SqlSource so, String id) throws IllegalAccessException {
        switch (so) {
            case StaticSqlSource o -> {
                setSqlId(o, "sql", id);
            }
            case ProviderSqlSource o -> { /* NOTHING_TODO */ }
            case DynamicSqlSource o -> {
                Field f = FieldUtils.getField(DynamicSqlSource.class, "rootSqlNode", true);
                setSqlId((SqlNode) f.get(o), id);
            }
            case RawSqlSource o -> {
                Field f = FieldUtils.getField(RawSqlSource.class, "sqlSource", true);
                setSqlId((SqlSource) f.get(o), id);
            }
            default -> throw new IllegalStateException("Unexpected SqlSource Type: " + so.getClass());
        }
    }

    @SuppressWarnings("unchecked")
    private void setSqlId(SqlNode so, String id) throws IllegalAccessException {
        switch (so) {
            case ChooseSqlNode o -> { /* NOTHING_TODO */ }
            case ForEachSqlNode o -> { /* NOTHING_TODO */ }
            case IfSqlNode o -> { /* NOTHING_TODO */ }
            case MixedSqlNode o -> {
                Field f = FieldUtils.getField(DynamicSqlSource.class, "contents", true);
                setSqlId(((List<SqlNode>) f.get(o)).getFirst(), id);
            }
            case StaticTextSqlNode o -> {
                setSqlId(o, "text", id);
            }
            case TextSqlNode o -> {
                setSqlId(o, "text", id);
            }
            case TrimSqlNode o -> {
                Field f = FieldUtils.getField(DynamicSqlSource.class, "contents", true);
                setSqlId((SqlNode) f.get(o), id);
            }
            case VarDeclSqlNode o -> { /* NOTHING_TODO */ }
            default -> throw new IllegalStateException("Unexpected SqlNode Type: " + so.getClass());
        }
    }

    private void setSqlId(Object fo, String fn, String id) throws IllegalAccessException {
        Field f = FieldUtils.getField(fo.getClass(), fn, true);
        String s = "%n/* MYBAtiS_SQLID %s */%n%s".formatted(id, f.get(fo));
        f.set(fo, s);
    }
}
