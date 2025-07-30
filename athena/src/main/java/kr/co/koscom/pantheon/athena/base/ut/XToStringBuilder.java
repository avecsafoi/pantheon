package kr.co.koscom.pantheon.athena.base.ut;

import kr.co.koscom.pantheon.athena.base.io.data.XData;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAPrintMask;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class XToStringBuilder {

    public static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static String toString(Object o) {
        if (o == null) return "";
        StringBuilder sb = new StringBuilder();
        build(sb, null, o.getClass(), o, 0, List.of());
        return sb.toString();
    }

    public static <vavr> void build(StringBuilder sb, Field f, Class<?> c, Object o, int d, List<Object> po) {
        if (c == null || o == null) return;
        if (c.isPrimitive()) {
            if (char.class.isAssignableFrom(c)) sb.append("'").append(o).append("'");
            else if (byte.class.isAssignableFrom(c)) sb.append("x%02X".formatted((byte) o));
            else sb.append(o);
        } else if (o instanceof String s) {
            if (f != null) {
                XAPrintMask a = f.getAnnotation(XAPrintMask.class);
                if (a != null) {
                    sb.append(StringU.toPrintMask(o.toString(), a));
                    return;
                }
            }
            sb.append('"').append(s.replaceAll("\"", "\\\\\"")).append('"');
        } else if (o instanceof Number) {
            if (o instanceof Byte b) sb.append("x%02X".formatted(b));
            else sb.append(o);
        } else if (c.isArray()) {
            int z = Array.getLength(o);
            String ds = StringUtils.repeat(' ', d++ * 2);
            sb.append("[");
            if (z > 0) {
                List<Object> l = new ArrayList<>(po);
                l.add(o);
                for (int i = 0; i < z; ) {
                    if (i > 0) sb.append(",").append(System.lineSeparator());
                    if (z > 1 && d > 1) sb.append(ds);
                    sb.append("%2d: ".formatted(++i));
                    Object fo = Array.get(o, i);
                    if (fo != null) {
                        if (po.contains(fo)) sb.append("REF");
                        else build(sb, f, fo.getClass(), fo, d, l);
                    }
                }
                sb.append("]");
            }
        } else if (o instanceof List<?> lo) {
            int z = lo.size();
            String ds = StringUtils.repeat(' ', d++ * 2);
            sb.append("[");
            if (z > 0) {
                List<Object> l = new ArrayList<>(po);
                l.add(o);
                int i = 0;
                for (Object fo : lo) {
                    if (i > 0) sb.append(",").append(System.lineSeparator());
                    if (z > 1 && d > 1) sb.append(ds);
                    sb.append("%2d: ".formatted(++i));
                    if (fo != null) {
                        if (po.contains(fo)) sb.append("REF");
                        else build(sb, f, fo.getClass(), fo, d, l);
                    }
                }
                sb.append("]");
            }
        } else if (o instanceof XData) {
            Field[] fa = Arrays.stream(ClassFieldsCache.getFields(c)).filter(ClassFieldsCache.NSTATIC).toArray(Field[]::new);
            int z = fa.length;
            String ds = StringUtils.repeat(' ', d++ * 2);
            sb.append("{");
            if (z > 0) {
                List<Object> l = new ArrayList<>(po);
                l.add(o);
                int i = 0;
                for (Field x : fa) {
                    if (i > 0) sb.append(",").append(System.lineSeparator());
                    if (z > 1 && d > 1) sb.append(ds);
                    sb.append("%2d.%s: ".formatted(++i, x.getName()));
                    Object fo;
                    try {
                        fo = FieldUtils.readField(x, o, true);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    if (fo != null) {
                        if (po.contains(fo)) sb.append("REF");
                        else build(sb, x, fo.getClass(), fo, d, l);
                    }
                }
                sb.append("}");
            }
        } else if (o instanceof Map<?, ?> m) {
            int z = m.size();
            String ds = StringUtils.repeat(' ', d++ * 2);
            sb.append("{");
            if (z > 0) {
                List<Object> l = new ArrayList<>(po);
                l.add(o);
                int i = 0;
                for (Map.Entry<?, ?> x : m.entrySet()) {
                    if (i > 0) sb.append(",").append(System.lineSeparator());
                    if (z > 1 && d > 1) sb.append(ds);
                    sb.append("%2d.%s: ".formatted(++i, x.getKey()));
                    Object fo = x.getValue();
                    if (fo != null) {
                        if (po.contains(fo)) sb.append("REF");
                        else build(sb, null, fo.getClass(), fo, d, l);
                    }
                }
                sb.append("}");
            }
        } else {
            if (Character.class.isAssignableFrom(c)) sb.append("'").append(o).append("'");
            else if (o instanceof Date t) sb.append('"').append(DF.format(t)).append('"');
            else sb.append(o);
        }
    }
}
