package kr.co.koscom.pantheon.athena.base.io;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

public class KDataUtils {

    public static final String DEF_DATE_FORMAT_08 = "yyyyMMdd";
    public static final String DEF_DATE_FORMAT_12 = "yyyyMMddHHmm";
    public static final String DEF_DATE_FORMAT_14 = "yyyyMMddHHmmss";
    public static final String DEF_DATE_FORMAT = DEF_DATE_FORMAT_14;

    public static Object createObject(Class<?> c, Field f) throws IOException {
        try {
            return c.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new IOException("Faile to create Object: " + en(c, f), e);
        }
    }

    public static String en(Class<?> c, Field f) {
        return f == null ? c.getSimpleName() : "%s of %s.%s".formatted(c.getSimpleName(), f.getDeclaringClass().getSimpleName(), f.getName());
    }

    public static List<Field> getAnnotationFields(Class<?> c, Class<? extends Annotation> t) {
        Class<?> s = c.getSuperclass();
        List<Field> l = (s != null && !s.isInterface()) ? getAnnotationFields(s, t) : new LinkedList<>();
        for (Field f : c.getDeclaredFields())
            if (!Modifier.isStatic(f.getModifiers()) && !Modifier.isTransient(f.getModifiers()))
                if (t == null) l.add(f);
                else for (Annotation a : f.getDeclaredAnnotations())
                    if (a.annotationType().equals(t)) {
                        l.add(f);
                        break;
                    }
        return l;
    }
}
