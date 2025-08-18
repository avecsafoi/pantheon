package kr.co.koscom.olympus.pb.ab.plugin;

import com.mybatisflex.core.datasource.DataSourceShardingStrategy;

import java.lang.reflect.Method;

public class PBDataSourceShardingStrategy implements DataSourceShardingStrategy {

    public String doSharding(String k, Object mapper, Method mapperMethod, Object[] methodArgs) {
        String n = mapperMethod.getName();
        if (k != null && n.matches("(insert|update|delete)")) {
            return k.replaceAll("[03456789]\\d*$", "");
        }
        return k;
    }
}
