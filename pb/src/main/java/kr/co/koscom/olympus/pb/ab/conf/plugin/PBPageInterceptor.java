package kr.co.koscom.olympus.pb.ab.conf.plugin;

import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.db.page.*;
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

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
@Slf4j
public class PBPageInterceptor implements Interceptor {

    public static final DefaultReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    private static void setPageSql(MappedStatement ms, BoundSql bs, StringBuilder sb, Map.Entry<String, PBPage> pe) {

        List<ParameterMapping> pm = bs.getParameterMappings();
        String pn = pe.getKey();
        PBPage pg = pe.getValue();

        if (pg instanceof PBCPage cpg) {
            String on = pn.isEmpty() ? "orders" : pn + ".orders";
            String ln = pn.isEmpty() ? "limit" : pn + ".limit";
            List<PBOrder> os = cpg.getOrders();
            StringBuilder bw = new StringBuilder();
            StringBuilder bo = new StringBuilder();
            int i = 0, z = os.size(), x = z - 1;
            for (PBOrder o : os) {
                if (!cpg.isFirst()) {
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
            if (!cpg.isFirst()) bw.append(")".repeat(Math.max(0, i * 2 - 1)));
            pm.add(new ParameterMapping.Builder(ms.getConfiguration(), ln, int.class).build());
            sb.append("SELECT A.* FROM (%n%s%n) A%s%n%s%nLIMIT ?".formatted(bs.getSql(), bw, bo));
        } else if (pg instanceof PBNPage) {
            String on = pn.isEmpty() ? "" : pn + ".";
            pm.add(new ParameterMapping.Builder(ms.getConfiguration(), on + "offset", Object.class).build());
            pm.add(new ParameterMapping.Builder(ms.getConfiguration(), on + "limit", Object.class).build());
            sb.append(" LIMIT ?, ?");
        }
    }

    public static void setLockSql(MappedStatement ms, BoundSql bs, StringBuilder sb, Map.Entry<String, PBLock> le) {
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

    public static <T> Map.Entry<String, T> findTypeEntry(Object o, Class<T> t) {
        return findTypeEntry(o, t, "");
    }

    @SuppressWarnings("unchecked")
    public static <T> Map.Entry<String, T> findTypeEntry(Object o, Class<T> t, String p) {
        if (o == null) return null;
        Class<?> c = o.getClass();
        List<Map.Entry<String, Object>> ls = null;
        if (t.isAssignableFrom(c)) {
            return Map.entry(p, (T) o);
        } else if (o instanceof PBData) {
            List<Field> l = FieldUtils.getAllFieldsList(c);
            try {
                ls = new ArrayList<>(l.size());
                for (Field f : l) {
                    Object x = FieldUtils.readField(f, o, true);
                    if (x == null) continue;
                    String y = p.isEmpty() ? f.getName() : p + "." + f.getName();
                    if (t.isAssignableFrom(x.getClass())) return Map.entry(y, (T) x);
                    ls.add(Map.entry(y, x));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else if (o instanceof Map<?, ?> m) {
            ls = new ArrayList<>(m.size());
            for (Map.Entry<?, ?> e : m.entrySet()) {
                Object x = e.getValue();
                if (x == null) continue;
                String k = e.getKey().toString();
                String y = p.isEmpty() ? k : p + "." + k;
                if (t.isAssignableFrom(x.getClass())) return Map.entry(y, (T) x);
                ls.add(Map.entry(y, x));
            }
        } else if (o instanceof List<?> l) {
            int n = l.size();
            ls = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                Object x = l.get(i);
                String y = n + "[" + i + "]";
                if (t.isAssignableFrom(x.getClass())) return Map.entry(y, (T) x);
                ls.add(Map.entry(y, x));
            }
        } else if (c.isArray()) {
            int n = Array.getLength(o);
            ls = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                Object x = Array.get(o, i);
                String y = n + "[" + i + "]";
                if (t.isAssignableFrom(x.getClass())) return Map.entry(y, (T) x);
                ls.add(Map.entry(y, x));
            }
        }
        if (ls != null) {
            for (Map.Entry<String, Object> e : ls) {
                Map.Entry<String, T> r = findTypeEntry(e.getValue(), t, e.getKey());
                if (r != null) return r;
            }
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
            Map.Entry<String, PBPage> pe = findTypeEntry(pr, PBPage.class);
            Map.Entry<String, PBLock> le = findTypeEntry(pr, PBLock.class);

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
                PBPage pg = pe.getValue();
                if (pg instanceof PBCPage cpg) {
                    if (rs instanceof List<?> l) {
                        if (l.size() < cpg.getLimit()) cpg.setLast(true);
                        else if (!l.isEmpty()) {
                            Object r = l.getLast();
                            if (r instanceof Map<?, ?> m)
                                for (PBOrder o : cpg.getOrders())
                                    o.setValue(m.get(o.getColumn()));
                            else
                                for (PBOrder o : cpg.getOrders())
                                    o.setValue(FieldUtils.readField(r, o.getColumn(), true));
                        }
                    }
                } else if (pg instanceof PBNPage npg) {
                    if (rs instanceof List<?> l) {
                        if (l.size() < npg.getLimit()) npg.setLast(true);
                    }
                }
            }
            return rs;
        }
        return invocation.proceed();
    }
}
