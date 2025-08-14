package kr.co.koscom.olympus.pb.ab.db.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.constant.SqlOperator;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.table.TableInfo;
import com.mybatisflex.core.table.TableInfoFactory;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBOrder;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface PBMapper<T> extends BaseMapper<T> {

    /**
     * 로직수정: @Id(pkArgs) 가 여러개일 때, 첫번째 @Id 하나만 확인하는 로직을 모든 @Id 확인하도록 수정
     * : @Id 가 long type 이면서 값이 0 이면, 값이 없는 것으로 간주하여 INSERT 처리
     * : Long = 0 은 Update
     */
    @Override
    default int insertOrUpdate(T entity, boolean ignoreNulls) {
        TableInfo tableInfo = TableInfoFactory.ofEntityClass(entity.getClass());
        Object[] pkArgs = tableInfo.buildPkSqlArgs(entity);
        boolean x = Arrays.stream(pkArgs).anyMatch(o -> o == null || (o instanceof String s && s.isEmpty()));
        return x ? insert(entity, ignoreNulls) : update(entity, ignoreNulls);
    }

    default List<T> cpage(PBCPage pg) {
        return cpage(pg, null);
    }

    default List<T> cpage(PBCPage pg, QueryWrapper qw) {
        QueryWrapper q = qw == null ? new QueryWrapper() : QueryWrapper.create().from(qw);
        if (pg.getOrders() != null) {
            int i = 0, z = pg.getOrders().size();
            QueryCondition c = null;
            for (PBOrder o : pg.getOrders()) {
                if (!pg.isFirst()) {
                    QueryCondition c1 = QueryCondition.create(new QueryColumn(o.getColumn()), o.isAsc() ? SqlOperator.GT : SqlOperator.LT, o.getValue());
                    if (c == null) q.and(c1);
                    else c.and(c1);
                    if (i++ < z) {
                        QueryCondition c2 = QueryCondition.create(new QueryColumn(o.getColumn()), SqlOperator.EQUALS, o.getValue());
                        c1.or(c2);
                        c = c2;
                    } else c = c1;
                }
                q.orderBy(o.getColumn(), o.isAsc());
            }
        }
        q.limit(pg.getLimit());
        List<T> l = this.selectListByQuery(q);
        boolean b = l.isEmpty() || l.size() < pg.getLimit();
        if (b) pg.setLast(b);
        else {
            T o = l.getLast();
            if (o instanceof Map<?, ?> m) pg.getOrders().forEach(x -> x.setValue(m.get(x.getColumn())));
            else {
                Class<?> c = o.getClass();
                pg.getOrders().forEach(x -> {
                    Field f = FieldUtils.getField(c, x.getColumn());
                    if (f != null) {
                        try {
                            x.setValue(f.get(o));
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }
        return l;
    }
}