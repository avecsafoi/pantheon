package kr.co.koscom.olympus.pb.ab.plugin;

import com.mybatisflex.core.datasource.DataSourceShardingStrategy;

import java.lang.reflect.Method;

public class PBDataSourceShardingStrategy implements DataSourceShardingStrategy {

    public String doSharding(String currentDataSourceKey, Object mapper, Method mapperMethod, Object[] methodArgs) {
        String n = mapperMethod.getName();
        if (n.matches("(insert|update|delete)")) return "ds";
        return currentDataSourceKey;
    }
}
