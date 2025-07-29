package kr.co.koscom.pantheon.athena.base.db.plugins.page;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
@Slf4j
public class XMyBatisInterceptor implements Interceptor {

    public static final DefaultReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    public static final ObjectMapper om = new ObjectMapper();

    private static void setPageSql(MappedStatement ms, BoundSql bs, String pn, XPage pg) {
        List<ParameterMapping> pm = bs.getParameterMappings();
        String on = pn.isEmpty() ? "orders" : pn + ".orders";
        String ln = pn.isEmpty() ? "" : pn + ".";
        List<XOrder> os = pg.getOrders();
        StringBuilder bw = new StringBuilder();
        StringBuilder bo = new StringBuilder();
        int i = 0, z = os.size(), x = z - 1;
        for (XOrder o : os) {
            if (!pg.isFirst()) {
                bw.append("%n%s (%s %s ?".formatted(i == 0 ? " WHERE" : "   AND", o.getColumn(), o.isAsc() ? ">" : "<"));
                String s1 = "%s[%d].value".formatted(on, i);
                pm.add(new ParameterMapping.Builder(ms.getConfiguration(), s1, Object.class).build());
                if (i < x) {
                    bw.append(" OR (%s = ?".formatted(o.getColumn()));
                    pm.add(new ParameterMapping.Builder(ms.getConfiguration(), s1, Object.class).build());
                }
            }
            bo.append("%s %s %s".formatted(i == 0 ? " ORDER BY" : ",", o.getColumn(), o.isAsc() ? "asc" : "desc"));
            i++;
        }
        if (!pg.isFirst()) bw.append(")".repeat(Math.max(0, i * 2 - 1)));
        pm.add(new ParameterMapping.Builder(ms.getConfiguration(), "%slimit".formatted(ln), int.class).build());
        MetaObject meta = MetaObject.forObject(bs, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        String sql = "%n/* MYBATIS_SQLID %s */%nSELECT A.* FROM (%n%s%n) A%s%n%s%nLIMIT ?".formatted(ms.getId(), bs.getSql(), bw, bo);
        meta.setValue("sql", sql);
    }

    @SuppressWarnings("unchecked")
    public static <T> Map.Entry<String, T> findType(Object o, Class<T> t) {
        if (o != null) {
            if (o instanceof Map<?, ?> m)
                for (Map.Entry<?, ?> e : m.entrySet())
                    if (t.isAssignableFrom(e.getValue().getClass())) return (Map.Entry<String, T>) e;
            if (t.isAssignableFrom(o.getClass())) return Map.entry("", (T) o);
        }
        return null;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof Executor executor) {
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object pr = args[1];
            RowBounds rb = (RowBounds) args[2];
            Map.Entry<String, XPage> pe = findType(pr, XPage.class);
            if (pe == null) return invocation.proceed();
            XPage pg = pe.getValue();
            BoundSql bs = ms.getBoundSql(pr);
            setPageSql(ms, bs, pe.getKey(), pg);
            CacheKey ck = executor.createCacheKey(ms, pr, rb, bs);
            Object rs = executor.query(ms, pr, rb, (ResultHandler<?>) args[3], ck, bs);
            if (rs instanceof List<?> l) {
                if (l.size() < pg.getLimit()) pg.setLast(true);
                else if (!l.isEmpty()) {
                    Object r = l.getLast();
                    Map<?, ?> m = r instanceof Map<?, ?> m1 ? m1 : om.convertValue(r, Map.class);
                    for (XOrder o : pg.getOrders()) o.setValue(m.get(o.getColumn()));
                }
            }
            return rs;
        }
        return invocation.proceed();
    }
}
