package kr.co.koscom.pantheon.athena.base.ut;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class ClassFieldsCache {

    static final Map<Class<?>, Field[]> FIELDS = new HashMap<>();

    public static final Predicate<Field> NSTATIC = f -> !Modifier.isStatic(f.getModifiers());

    public static Field[] getFields(Class<?> c) {
        return getFields(c, FIELDS);
    }

    protected static Field[] getFields(Class<?> c, Map<Class<?>, Field[]> m) {
        Field[] r = m.get(c);
        return r == null ? m.put(c, FieldUtils.getAllFields(c)) : r;
    }

    public static Field[] getFieldsByFilter(Class<?> c, Predicate<Field> t) {
        return Arrays.stream(getFields(c, FIELDS)).filter(t).toArray(Field[]::new);
    }

    protected static Field[] getFieldsByFilter(Class<?> c, Map<Class<?>, Field[]> m, Predicate<Field> t) {
        Field[] r = m.get(c);
        if (r == null) {
            r = Arrays.stream(getFields(c)).filter(t).toArray(Field[]::new);
            m.put(c, r);
        }
        return r;
    }
}
