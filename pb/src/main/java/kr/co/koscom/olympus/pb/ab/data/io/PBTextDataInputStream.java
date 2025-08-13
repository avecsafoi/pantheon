package kr.co.koscom.olympus.pb.ab.data.io;

import jakarta.annotation.Nonnull;
import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PBTextDataInputStream extends PBDataInputStream {

    public PBTextDataInputStream(InputStream in) {
        super(in);
    }

    public PBTextDataInputStream(InputStream in, Charset charset) {
        super(in, charset);
    }

    public PBTextDataInputStream(byte[] b) {
        super(b);
    }

    public PBTextDataInputStream(byte[] b, Charset charset) {
        super(b, charset);
    }


    @SuppressWarnings("unchecked")
    @Override
    public <X> X readObject(@Nonnull Class<X> c) throws IOException {
        return (X) readObject(c, null);
    }

    @Override
    public void readObject(@Nonnull Object o) throws IOException {
        readFields(o.getClass(), o);
    }

    private String readString(int n) throws IOException {
        byte[] b = new byte[n];
        super.readFully(b, 0, b.length);
        return new String(b, super.charset).trim();
    }

    private Object readObject(Class<?> c, Field f) throws IOException {

        if (PBData.class.isAssignableFrom(c)) {
            try {
                PBData o = (PBData) c.getConstructor().newInstance();
                o.readPBData(this);
                return o;
            } catch (Exception e) {
                throw new IOException(e);
            }
        }

        PBA a = f == null ? null : f.getAnnotation(PBA.class);
        if (a == null) throw new IOException("Field annotation required (@%s)".formatted(PBA.class.getSimpleName()));

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
            List<Object> l = new ArrayList<>(z);
            for (int i = 0; i < z; i++) l.add(readObject(s, f));
            return l;
        }
        if (c.isArray()) {
            int z = a.fix() ? a.scale() : NumberUtils.toInt(readString(a.scale()));
            Object o = Array.newInstance(c.getComponentType(), z);
            for (int i = 0; i < z; i++) Array.set(o, i, readObject(c.getComponentType(), f));
            return o;
        }
        if (Date.class.isAssignableFrom(c)) {
            if (a.format().isEmpty())
                throw new IOException("Format required of @%s(format = ?)".formatted(PBA.class.getSimpleName()));
            String s = readString(a.scale());
            try {
                return s.isEmpty() ? null : DateUtils.parseDate(s, a.format());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IOException("Unexpected type (%s)".formatted(c.getCanonicalName()));
    }

    public Object readFields(Class<?> c, Object o) throws IOException {
        if (o == null) try {
            o = c.getConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new IOException(e);
        }
        Class<?> s = c.getSuperclass();
        if (s != null && !s.isInterface()) readFields(s, o);
        for (Field f : c.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) continue;
            if (!f.canAccess(o)) f.setAccessible(true);
            try {
                f.set(o, readObject(f.getType(), f));
            } catch (Throwable e) {
                throw new IOException("Failed to read field (%s.%s): %s".formatted(c.getCanonicalName(), f.getName(), e.getMessage()), e);
            }
        }
        return o;
    }
}
