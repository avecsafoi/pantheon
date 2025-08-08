package kr.co.koscom.olympus.pb.include.io;

import jakarta.annotation.Nonnull;
import kr.co.koscom.olympus.pb.include.PB_A;
import kr.co.koscom.olympus.pb.include.PB_Data;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PB_TextDataInputStream extends PB_DataInputStream {

    public PB_TextDataInputStream(InputStream in) {
        super(in);
    }

    public PB_TextDataInputStream(InputStream in, Charset charset) {
        super(in, charset);
    }

    public PB_TextDataInputStream(byte[] b) {
        super(b);
    }

    public PB_TextDataInputStream(byte[] b, Charset charset) {
        super(b, charset);
    }


    @Override
    public <X> X readObject(@Nonnull Class<X> c) throws Throwable {
        @SuppressWarnings("unchecked")
        X x = (X) readObject(c, null);
        return x;
    }

    private String readString(int n) throws IOException {
        byte[] b = new byte[n];
        super.readFully(b, 0, b.length);
        return new String(b, super.charset).trim();
    }

    private Object readObject(Class<?> c, Field f) throws Throwable {

        if (PB_Data.class.isAssignableFrom(c)) return readFields(c);

        PB_A a = f == null ? null : f.getAnnotation(PB_A.class);
        if (a == null) throw new IOException("Field annotation required (@%s)".formatted(PB_A.class.getSimpleName()));

        if (String.class.isAssignableFrom(c)) {
            int z = a.fix() ? a.scale() : NumberUtils.toInt(readString(a.scale()));
            return readString(z);
        }
        if (c.isPrimitive()) {
            if (int.class.equals(c)) {
                String s = readString(a.scale());
                return NumberUtils.toInt(s, 0);
            }
            if (long.class.equals(c)) {
                String s = readString(a.scale());
                return NumberUtils.toLong(s, 0);
            }
            if (double.class.equals(c)) {
                String s = readString(a.scale());
                return NumberUtils.toDouble(s, 0);
            }
            if (float.class.equals(c)) {
                String s = readString(a.scale());
                return NumberUtils.toFloat(s, 0);
            }
            if (short.class.equals(c)) {
                String s = readString(a.scale());
                return NumberUtils.toShort(s, (short) 0);
            }
            throw new IOException("Unexpected primitive type (%s)".formatted(c.getCanonicalName()));
        }
        if (Number.class.isAssignableFrom(c)) {
            if (BigDecimal.class.equals(c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.createBigDecimal(s);
            }
            if (BigInteger.class.equals(c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.createBigDecimal(s).toBigInteger();
            }
            if (Integer.class.equals(c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.toInt(s);
            }
            if (Long.class.equals(c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.toLong(s);
            }
            if (Double.class.equals(c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.toDouble(s);
            }
            if (Float.class.equals(c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.toFloat(s);
            }
            if (Short.class.equals(c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.toShort(s);
            }
            throw new IOException("Unexpected Number type (%s)".formatted(c.getCanonicalName()));
        }
        if (List.class.isAssignableFrom(c)) {
            Class<?> s = (Class<?>) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0];
            int z = a.fix() ? a.scale() : NumberUtils.toInt(readString(a.scale()));
            List<Object> o = new ArrayList<>(z);
            for (int i = 0; i < z; i++) o.add(readObject(s, f));
            return o;
        }
        if (c.isArray()) {
            int z = a.fix() ? a.scale() : NumberUtils.toInt(readString(a.scale()));
            Object o = Array.newInstance(c.getComponentType(), z);
            for (int i = 0; i < z; i++) Array.set(o, i, readObject(c.getComponentType(), f));
            return o;
        }
        if (Date.class.isAssignableFrom(c)) {
            if (a.format().isEmpty())
                throw new IOException("Format required of @%s(format = ?)".formatted(PB_A.class.getSimpleName()));
            String s = readString(a.scale());
            return s.isEmpty() ? null : DateUtils.parseDate(s, a.format());
        }
        throw new IOException("Unexpected type (%s)".formatted(c.getCanonicalName()));
    }

    private Object readFields(Class<?> c) throws Throwable {
        Object o = c.getConstructor().newInstance();
        Class<?> s = c.getSuperclass();
        if (s != null && !s.isInterface()) readFields(s);
        for (Field f : c.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) continue;
            if (!f.canAccess(o)) f.setAccessible(true);
            try {
                f.set(o, readObject(f.getType(), f));
            } catch (Throwable t) {
                throw new Throwable("Failed to read field (%s.%s): %s".formatted(c.getCanonicalName(), f.getName(), t.getMessage()), t);
            }
        }
        return o;
    }
}
