package kr.co.koscom.olympus.pb.ab.plugin;

import com.mybatisflex.core.datasource.DataSourceShardingStrategy;

import java.lang.reflect.Method;

public class PBDataSourceShardingStrategy implements DataSourceShardingStrategy {

    public String doSharding(String currentDataSourceKey, Object mapper, Method mapperMethod, Object[] methodArgs) {
        String mn = mapperMethod.getName();
        if (mn.matches("(insert|update|delete)")) return "ds1";
        return currentDataSourceKey;
    }
}
