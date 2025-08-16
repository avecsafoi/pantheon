package kr.co.koscom.olympus.pb.ab.util;

import kr.co.koscom.olympus.pb.ab.conf.PBAppConfig;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class PBDataUtil {

    @SuppressWarnings("unchecked")
    public static <X> X findPBService(String id) {
        return (X) PBAppConfig.PBServiceMap.get("PB_SID " + id);
    }

    public static ParameterizedType findInterfaceParameterizedType(Type t, Class<?> f) {
        if (t instanceof ParameterizedType p) {
            Class<?> y = (Class<?>) p.getRawType();
            if (f.isAssignableFrom(y)) return p;
            return findInterfaceParameterizedType(p.getRawType(), f);
        } else if (t instanceof Class<?> c) {
            for (Type x : c.getGenericInterfaces()) {
                ParameterizedType p = findInterfaceParameterizedType(x, f);
                if (p != null) return p;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T createObject(Type t) {
        return createObject((Class<T>) t);
    }

    @SuppressWarnings("unchecked")
    public static <T> T createObject(Class<?> c) {
        try {
            return (T) c.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T createObject(Class<?> c, Class<?>[] a, Object[] o) {
        try {
            return (T) c.getConstructor(a).newInstance(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
