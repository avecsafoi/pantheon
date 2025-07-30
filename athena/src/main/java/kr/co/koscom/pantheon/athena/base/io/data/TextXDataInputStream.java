package kr.co.koscom.pantheon.athena.base.io.data;

import com.alibaba.fastjson2.util.DateUtils;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAText;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static kr.co.koscom.pantheon.athena.base.io.data.XDataUtils.createObject;
import static kr.co.koscom.pantheon.athena.base.io.data.XDataUtils.en;


public class TextXDataInputStream extends XDataInputStream {

    public TextXDataInputStream(InputStream is, Charset charset) {
        super(is, charset);
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
        return (X) readFields(c, XDataUtils.createObject(c, null));
    }

    public Object readObject(Class<?> c, Field f) throws IOException {
        XAText aa = f == null ? null : f.getAnnotation(XAText.class);
        if (aa == null)
            throw new IOException("Needed @%s for Field: %s".formatted(XAText.class.getSimpleName(), en(c, f)));
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
                throw new IOException("Needed @%s(format={value}) for Date: %s".formatted(XAText.class.getSimpleName(), en(c, f)));
            String s = readString(z).trim();
            return s.isEmpty() ? null : DateUtils.parseDate(s, df);
        }
        if (List.class.isAssignableFrom(c)) {
            Class<?> s = XDataUtils.getParameterizedType(f);
            List<Object> l = new ArrayList<>(z);
            for (int i = 0; i < z; i++) l.add(readFields(s, createObject(s, f)));
            return l;
        }
        if (XData.class.isAssignableFrom(c)) {
            return readFields(c, createObject(c, f));
        }
        throw new IOException("Unsupported Object type: " + en(c, f));
    }

    public Object readFields(Class<?> c, Object o) throws IOException {
        Field[] fa = XDataFieldsCache.getInputTextXDataFields(c);
        for (Field f : fa) {
            if (!f.canAccess(o)) f.setAccessible(true);
            Object fo;
            try {
                fo = readObject(f.getType(), f);
            } catch (IOException e) {
                throw new IOException("Failed to read Object Field: " + en(c, f), e);
            }
            try {
                f.set(o, fo);
            } catch (IllegalAccessException e) {
                throw new IOException("Failed to set Field: " + en(c, f), e);
            }
        }
        return o;
    }
}
