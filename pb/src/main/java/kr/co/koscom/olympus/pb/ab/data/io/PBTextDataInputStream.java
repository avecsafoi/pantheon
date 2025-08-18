package kr.co.koscom.olympus.pb.ab.data.io;

import jakarta.annotation.Nonnull;
import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static kr.co.koscom.olympus.pb.ab.util.PBDataUtil.createObject;

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
        PBDataT t = new PBDataT(null, c, null, null, null);
        return (X) readObject(t);
    }

    @Override
    public void readPBData(@Nonnull Class<?> c, Object o) throws IOException {
        PBDataT t = new PBDataT(null, c, o, null, null);
        readFields(t);
    }

    public String readString(int n) throws IOException {
        return readString(n, null);
    }

    public String readString(int n, PBA a) throws IOException {
        byte[] b = new byte[n];
        super.readFully(b, 0, b.length);
        Charset c = a == null || a.charset().isEmpty() ? super.charset : Charset.forName(a.charset());
        return new String(b, c).trim();
    }

    public Object readObject(PBDataT t) throws IOException {

        if (PBData.class.isAssignableFrom(t.c)) {
            if (t.refClass(t.c)) throw new IOException("Found recursive class (%s)".formatted(t.c.getCanonicalName()));
            if (t.c.isInterface()) return null;
            PBData x = createObject(t.c);
            x.readPBData(this);
            return x;
        }

        PBA a = t.a;
        if (a == null) throw new IOException("Field annotation required (@%s)".formatted(PBA.class.getSimpleName()));

        if (String.class.isAssignableFrom(t.c)) {
            int z = a.fix() ? a.scale() : NumberUtils.toInt(readString(a.scale()));
            return readString(z, a);
        }
        if (t.c.isPrimitive()) {
            if (int.class.equals(t.c)) {
                String s = readString(a.scale());
                return NumberUtils.toInt(s, 0);
            }
            if (long.class.equals(t.c)) {
                String s = readString(a.scale());
                return NumberUtils.toLong(s, 0);
            }
            if (double.class.equals(t.c)) {
                String s = readString(a.scale());
                return NumberUtils.toDouble(s, 0);
            }
            if (float.class.equals(t.c)) {
                String s = readString(a.scale());
                return NumberUtils.toFloat(s, 0);
            }
            if (short.class.equals(t.c)) {
                String s = readString(a.scale());
                return NumberUtils.toShort(s, (short) 0);
            }
            throw new IOException("Unexpected primitive type (%s)".formatted(t.c.getCanonicalName()));
        }
        if (Number.class.isAssignableFrom(t.c)) {
            if (BigDecimal.class.equals(t.c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.createBigDecimal(s);
            }
            if (BigInteger.class.equals(t.c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.createBigDecimal(s).toBigInteger();
            }
            if (Integer.class.equals(t.c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.toInt(s);
            }
            if (Long.class.equals(t.c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.toLong(s);
            }
            if (Double.class.equals(t.c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.toDouble(s);
            }
            if (Float.class.equals(t.c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.toFloat(s);
            }
            if (Short.class.equals(t.c)) {
                String s = readString(a.scale());
                return s.isEmpty() ? null : NumberUtils.toShort(s);
            }
            throw new IOException("Unexpected Number type (%s)".formatted(t.c.getCanonicalName()));
        }
        if (List.class.isAssignableFrom(t.c)) {
            Class<?> s = (Class<?>) ((ParameterizedType) t.f.getGenericType()).getActualTypeArguments()[0];
            int z = a.fix() ? a.scale() : NumberUtils.toInt(readString(a.scale()));
            List<Object> l = new ArrayList<>(z);
            for (int i = 0; i < z; i++) {
                PBDataT u = new PBDataT(t, s, null, null, null);
                Object x = readObject(u);
                l.add(x);
            }
            return l;
        }
        if (t.c.isArray()) {
            Class<?> s = t.c.getComponentType();
            int z = a.fix() ? a.scale() : NumberUtils.toInt(readString(a.scale()));
            Object o = Array.newInstance(t.c.getComponentType(), z);
            for (int i = 0; i < z; i++) {
                PBDataT u = new PBDataT(t, s, null, null, null);
                Object x = readObject(u);
                Array.set(o, i, x);
            }
            return o;
        }
        if (Date.class.isAssignableFrom(t.c)) {
            if (a.format().isEmpty())
                throw new IOException("Format required of @%s(format = ?)".formatted(PBA.class.getSimpleName()));
            String s = readString(a.scale());
            try {
                return s.isEmpty() ? null : DateUtils.parseDate(s, a.format());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IOException("Unexpected type (%s)".formatted(t.c.getCanonicalName()));
    }

    public void readFields(PBDataT t) throws IOException {
        if (t.o == null) t.o = createObject(t.c);
        // List<Field> l = FieldUtils.getAllFieldsList(t.c).stream().filter(f -> f.getAnnotation(PBA.class) != null).toList();
        Class<?> s = t.c.getSuperclass();
        if (s != null && !s.isInterface() && PBData.class.isAssignableFrom(s) && s != PBObject.class && s != Object.class) {
            PBDataT u = new PBDataT(t, s, t.o, null, null);
            readFields(u);
        }
        for (Field f : t.c.getDeclaredFields()) {
            PBA a = f.getAnnotation(PBA.class);
            if (a == null || a.skip()) continue;
            if (!f.canAccess(t.o)) f.setAccessible(true);
            try {
                PBDataT u = new PBDataT(t, f.getType(), null, f, a);
                Object x = readObject(u);
                f.set(t.o, x);
            } catch (Throwable e) {
                throw new IOException("Failed to read field (%s.%s): %s".formatted(t.c.getCanonicalName(), f.getName(), e.getMessage()), e);
            }
        }
    }
}
