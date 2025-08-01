package kr.co.koscom.olympus.athena.base.db.plugins.page;

import kr.co.koscom.olympus.athena.base.db.plugins.XLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
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

    private static void setPageSql(MappedStatement ms, BoundSql bs, StringBuilder sb, Map.Entry<String, XPage> pe) {
        List<ParameterMapping> pm = bs.getParameterMappings();
        String pn = pe.getKey();
        XPage pg = pe.getValue();
        String on = pn.isEmpty() ? "orders" : pn + ".orders";
        String ln = pn.isEmpty() ? "limit" : pn + ".limit";
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
        pm.add(new ParameterMapping.Builder(ms.getConfiguration(), ln, int.class).build());
        sb.append("SELECT A.* FROM (%n%s%n) A%s%n%s%nLIMIT ?".formatted(bs.getSql(), bw, bo));
    }

    public static void setLockSql(MappedStatement ms, BoundSql bs, StringBuilder sb, Map.Entry<String, XLock> le) {
        int wt = le.getValue().waitTime();
        if (wt == Integer.MAX_VALUE) sb.append(" FOR UPDATE");
        else if (wt == 0) sb.append(" FOR UPDATE NOWAIT");
        else if (wt > 0) {
            sb.append(" FOR UPDATE WAIT ?");
            List<ParameterMapping> pm = bs.getParameterMappings();
            String nm = le.getKey().isEmpty() ? "waitTime" : le.getKey() + ".waitTime";
            pm.add(new ParameterMapping.Builder(ms.getConfiguration(), nm, Object.class).build());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Map.Entry<String, T> findTypeEntry(Object o, Class<T> t) {
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
            Map.Entry<String, XPage> pe = findTypeEntry(pr, XPage.class);
            Map.Entry<String, XLock> le = findTypeEntry(pr, XLock.class);

            BoundSql bs = ms.getBoundSql(pr);
            StringBuilder sb = new StringBuilder("%n/* MyBatis_SQLID %s */%n".formatted(ms.getId()));

            if (pe != null) setPageSql(ms, bs, sb, pe);
            else sb.append(bs.getSql());

            if (le != null) setLockSql(ms, bs, sb, le);

            MetaObject meta = MetaObject.forObject(bs, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
            meta.setValue("sql", sb.toString());

            CacheKey ck = executor.createCacheKey(ms, pr, rb, bs);
            Object rs = executor.query(ms, pr, rb, (ResultHandler<?>) args[3], ck, bs);
            if (pe != null) {
                XPage pg = pe.getValue();
                if (rs instanceof List<?> l) {
                    if (l.size() < pg.getLimit()) pg.setLast(true);
                    else if (!l.isEmpty()) {
                        Object r = l.getLast();
                        if (r instanceof Map<?, ?> m)
                            for (XOrder o : pg.getOrders())
                                o.setValue(m.get(o.getColumn()));
                        else
                            for (XOrder o : pg.getOrders())
                                o.setValue(FieldUtils.readField(r, o.getColumn(), true));
                    }
                }
            }
            return rs;
        }
        return invocation.proceed();
    }
}
