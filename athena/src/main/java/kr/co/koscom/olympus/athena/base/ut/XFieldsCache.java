package kr.co.koscom.olympus.athena.base.ut;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class XFieldsCache {

    public static final Predicate<Field> NSTATIC = f -> !Modifier.isStatic(f.getModifiers());
    static final Map<Class<?>, Field[]> FIELDS = new HashMap<>();

    public static Field[] getFields(Class<?> c) {
        return getFields(c, FIELDS);
    }

    protected static Field[] getFields(Class<?> c, Map<Class<?>, Field[]> m) {
        Field[] r = m.get(c);
        if (r == null) m.put(c, r = FieldUtils.getAllFields(c));
        return r;
    }

    public static Field[] getFieldsByFilter(Class<?> c, Predicate<Field> t) {
        return Arrays.stream(getFields(c, FIELDS)).filter(t).toArray(Field[]::new);
    }

    protected static Field[] getFieldsByFilter(Class<?> c, Map<Class<?>, Field[]> m, Predicate<Field> t) {
        Field[] r = m.get(c);
        if (r == null) m.put(c, r = Arrays.stream(getFields(c, FIELDS)).filter(t).toArray(Field[]::new));
        return r;
    }
}
