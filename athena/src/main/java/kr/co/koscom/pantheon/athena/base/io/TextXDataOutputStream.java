package kr.co.koscom.pantheon.athena.base.io;

import com.alibaba.fastjson2.util.DateUtils;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.co.koscom.pantheon.athena.base.io.XDataUtils.*;

public class TextXDataOutputStream extends DataOutputStream {

    public static final Map<Class<?>, Field[]> FIELDS = new HashMap<>();

    public final Charset charset;

    public TextXDataOutputStream(OutputStream os, Charset charset) {
        super(os);
        this.charset = charset;
    }

    public void writeString(Class<?> c, Field f, XAText aa, Object o, int p) throws IOException {
        byte[] b = o == null ? null : String.valueOf(o).getBytes(charset);
        int n = b == null ? 0 : b.length;
        int z = aa.fix() ? aa.size() : n;
        if (n > z) throw new IOException("Exceeded size %d by %d(%s): %s".formatted(z, n, o, en(c, f)));
        int d = z - n;
        if (p < 1) for (int i = 0; i < d; i++) write(' ');
        if (n > 0) write(b, 0, n);
        if (p > 0) for (int i = 0; i < d; i++) write(' ');
    }

    public void writeObject(Object o) throws IOException {
        assert o != null;
        writeFields(o.getClass(), o);
    }

    public void writeObject(Class<?> c, Object o, Field f) throws IOException {
        XAText aa = f == null ? null : f.getAnnotation(XAText.class);
        if (aa == null)
            throw new IOException("Needed @%s for Field: %s".formatted(XAText.class.getSimpleName(), en(c, f)));
        if (c.isPrimitive()) {
            if (int.class.isAssignableFrom(c) || long.class.isAssignableFrom(c) || float.class.isAssignableFrom(c) || double.class.isAssignableFrom(c)) {
                writeString(c, f, aa, o, 0);
                return;
            }
            throw new IOException("Unsupported primitive type: " + en(c, f));
        }
        if (String.class.isAssignableFrom(c)) {
            writeString(c, f, aa, o, 1);
            return;
        }
        if (Number.class.isAssignableFrom(c)) {
            if (BigDecimal.class.isAssignableFrom(c) || BigInteger.class.isAssignableFrom(c)) {
                writeString(c, f, aa, o, 0);
                return;
            }
            throw new IOException("Unsupported Number type: " + en(c, f));
        }
        if (Date.class.isAssignableFrom(c)) {
            String df = aa.format();
            if (df == null)
                throw new IOException("Needed @%s(format={value}) for Date: %s".formatted(XAText.class.getSimpleName(), en(c, f)));
            String s = DateUtils.format((Date) o, df);
            writeString(c, f, aa, s, 0);
            return;
        }
        if (List.class.isAssignableFrom(c)) {
            Class<?> s = getParameterizedType(f);
            @SuppressWarnings("unchecked")
            List<Object> l = (List<Object>) o;
            int n = l == null ? 0 : l.size();
            int z = aa.fix() ? aa.size() : n;
            if (n > z) throw new IOException("Exceeded size %d by %d(%s): %s".formatted(z, n, o, en(c, f)));
            for (int i = 0; i < z; i++) writeFields(s, i < n ? l.get(i) : null);
            return;
        }
        if (XData.class.isAssignableFrom(c)) {
            writeFields(c, o);
            return;
        }
        throw new IOException("Unsupported Object type: " + en(c, f));
    }

    public void writeFields(Class<?> c, Object o) throws IOException {
        Field[] fa = FIELDS.get(c);
        if (fa == null) {
            List<Field> l = getAnnotationFields(c, XAText.class);
            fa = new Field[l.size()];
            FIELDS.put(c, l.toArray(fa));
        }
        for (Field f : fa) {
            if (!f.canAccess(o)) f.setAccessible(true);
            Object fo;
            try {
                fo = f.get(o);
            } catch (IllegalAccessException e) {
                throw new IOException("Failed to access Field: " + en(c, f), e);
            }
            writeObject(f.getType(), fo, f);
        }
    }
}
