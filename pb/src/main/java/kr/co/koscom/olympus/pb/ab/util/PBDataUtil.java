package kr.co.koscom.olympus.pb.ab.util;

import kr.co.koscom.olympus.pb.PbApplication;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class PBDataUtil {

    @SuppressWarnings("unchecked")
    public static <X> X findPBService(String id) {
        return (X) PbApplication.PBServiceMap.get("PB_SID " + id);
    }

    public static ParameterizedType findInterfaceParameterizedType(Class<?> c, Class<?> f) {
        for (Type t : c.getGenericInterfaces()) {
            if (t instanceof ParameterizedType p) {
                Class<?> x = (Class<?>) p.getRawType();
                if (f.isAssignableFrom(x)) return p;
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
