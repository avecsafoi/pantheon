package kr.co.koscom.olympus.pb.include;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.table.TableInfo;
import com.mybatisflex.core.table.TableInfoFactory;

import java.util.Arrays;

public interface PB_Mapper<T> extends BaseMapper<T> {

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
}
