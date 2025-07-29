package kr.co.koscom.pantheon.athena.base.io;

import com.alibaba.fastjson2.util.DateUtils;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.KAText;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.*;

import static kr.co.koscom.pantheon.athena.base.io.KDataUtils.*;


public class TextKDataInputStream extends DataInputStream {

    public static final Map<Class<?>, Field[]> FIELDS = new HashMap<>();

    private final Charset charset;

    public TextKDataInputStream(InputStream is, Charset charset) {
        super(is);
        this.charset = charset;
    }

    public int readSize(int n) throws IOException {
        return Integer.parseInt(readString(n).trim());
    }

    public String readString(int n) throws IOException {
        byte[] b = new byte[n];
        readFully(b);
        return new String(b, charset);
    }

    @SuppressWarnings("unchecked")
    public <X> X readObject(Class<X> c) throws IOException {
        return (X) readFields(c, KDataUtils.createObject(c, null));
    }

    public Object readObject(Class<?> c, Field f) throws IOException {
        KAText aa = f == null ? null : f.getAnnotation(KAText.class);
        if (aa == null)
            throw new IOException("Needed @%s for Field: %s".formatted(KAText.class.getSimpleName(), en(c, f)));
        int z = aa.fix() ? aa.size() : readSize(aa.size());
        if (c.isPrimitive()) {
            if (int.class.isAssignableFrom(c)) return Integer.parseInt(readString(z).trim());
            if (long.class.isAssignableFrom(c)) return Long.parseLong(readString(z).trim());
            if (float.class.isAssignableFrom(c)) return Float.parseFloat(readString(z).trim());
            if (double.class.isAssignableFrom(c)) return Double.parseDouble(readString(z).trim());
            throw new IOException("Unsupported primitive type: " + en(c, f));
        }
        if (String.class.isAssignableFrom(c)) {
            return readString(z).trim();
        }
        if (Number.class.isAssignableFrom(c)) {
            if (BigDecimal.class.isAssignableFrom(c)) return new BigDecimal(readString(z).trim());
            if (BigInteger.class.isAssignableFrom(c)) return new BigInteger(readString(z).trim());
            throw new IOException("Unsupported Number type: " + en(c, f));
        }
        if (Date.class.isAssignableFrom(c)) {
            String df = aa.format();
            if (df == null)
                throw new IOException("Needed @%s(format={value}) for Date: %s".formatted(KAText.class.getSimpleName(), en(c, f)));
            String s = readString(z).trim();
            return s.isEmpty() ? null : DateUtils.parseDate(s, df);
        }
        if (List.class.isAssignableFrom(c)) {
            Class<?> s = KDataUtils.getParameterizedType(c, f);
            List<Object> l = new ArrayList<>(z);
            for (int i = 0; i < z; i++) l.add(readFields(s, createObject(s, f)));
            return l;
        }
        if (KData.class.isAssignableFrom(c)) {
            return readFields(c, createObject(c, f));
        }
        throw new IOException("Unsupported Object type: " + en(c, f));
    }

    public Object readFields(Class<?> c, Object o) throws IOException {
        Field[] fa = FIELDS.get(c);
        if (fa == null) {
            List<Field> l = getAnnotationFields(c, KAText.class);
            fa = new Field[l.size()];
            FIELDS.put(c, l.toArray(fa));
        }
        for (Field f : fa) {
            if (!f.canAccess(o)) f.setAccessible(true);
            Object fo = readObject(f.getType(), f);
            try {
                f.set(o, fo);
            } catch (IllegalAccessException e) {
                throw new IOException("Failed to access Field: " + en(c, f), e);
            }
        }
        return o;
    }
}
