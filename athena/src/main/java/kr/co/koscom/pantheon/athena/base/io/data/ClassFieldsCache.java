package kr.co.koscom.pantheon.athena.base.io.data;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassFieldsCache {

    static final Map<Class<?>, Field[]> FIELDS = new HashMap<>();

    public static Field[] getFields(Class<?> c) {
        return getFields(FIELDS, c);
    }

    static Field[] getFields(Map<Class<?>, Field[]> m, Class<?> c) {
        Field[] r = m.get(c);
        if (r == null) m.put(c, r = FieldUtils.getAllFields(c));
        return r;
    }

    protected static Field[] getFields(Map<Class<?>, Field[]> m, Class<?> c, Class<?> t) {
        Field[] r = m.get(c);
        if (r == null) {
            r = getFields(c);
            List<Field> l = new ArrayList<>(r.length);
            for (Field f : r) {
                int i = f.getModifiers();
                if (!Modifier.isStatic(i) && !Modifier.isTransient(i))
                    if (t == null) l.add(f);
                    else for (Annotation a : f.getDeclaredAnnotations())
                        if (a.annotationType().equals(t)) {
                            l.add(f);
                            break;
                        }
            }
            r = new Field[l.size()];
            l.toArray(r);
            m.put(c, r);
        }
        return r;
    }


}
