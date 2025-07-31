package kr.co.koscom.pantheon.athena.base.io.data;

import kr.co.koscom.pantheon.athena.base.io.data.annotations.XABinary;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAUnsigned;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import static kr.co.koscom.pantheon.athena.base.io.data.XDataUtils.en;


public class BinaryXDataOutputStream extends XDataOutputStream {

    public BinaryXDataOutputStream(OutputStream out, Charset charset) {
        super(out, charset);
    }

    public void writeSize(int v) throws IOException {
        writeShort(v);
    }

    public void writeUnsginedInt(long v) throws IOException {
        for (int i = 24; i >= 0; i -= 8) write((int) (v >>> i) & 255);
    }

    public void writeUnsginedLong(BigInteger o) throws IOException {
        long v = o == null ? 0 : o.longValueExact();
        for (int i = 56; i >= 0; i -= 8) write((int) (v >>> i) & 255);
    }

    public void writeDate(Date d) throws IOException {
        long l = d == null ? 0 : d.getTime();
        writeLong(l);
    }

    @Override
    public void writeObject(Object o) throws IOException {
        if (o == null) return;
        writeObject(o.getClass(), o, null, 0);
    }

    public void writeObject(Class<?> c, Object o, Field f, int h) throws IOException {
        if (c.isPrimitive()) {
            if (int.class.isAssignableFrom(c)) {
                XAUnsigned au = f == null ? null : f.getAnnotation(XAUnsigned.class);
                if (au == null) writeInt((int) o);
                else writeShort((int) o);
                return;
            }
            if (short.class.isAssignableFrom(c)) {
                writeShort((short) o);
                return;
            }
            if (long.class.isAssignableFrom(c)) {
                XAUnsigned au = f == null ? null : f.getAnnotation(XAUnsigned.class);
                if (au == null) writeLong((long) o);
                else writeUnsginedInt((long) o);
                return;
            }
            if (float.class.isAssignableFrom(c)) {
                writeDouble((float) o);
                return;
            }
            if (double.class.isAssignableFrom(c)) {
                writeDouble((double) o);
                return;
            }
            if (byte.class.isAssignableFrom(c)) {
                writeFloat((float) o);
                return;
            }
            if (char.class.isAssignableFrom(c)) {
                write((char) o);
                return;
            }
            if (boolean.class.isAssignableFrom(c)) {
                writeBoolean((boolean) o);
            }
            throw new IOException("Unsupported primitive type: " + en(c, f));
        }
        if (String.class.isAssignableFrom(c)) {
            String s = (String) o;
            byte[] b = s == null ? null : s.getBytes(charset);
            int n = b == null ? 0 : b.length;
            XABinary aa = f == null ? null : f.getAnnotation(XABinary.class);
            int z = aa == null ? n : aa.size();
            if (n > z) throw new IOException("Out of byteSize(%d): %s %d(%s)".formatted(z, en(c, f), n, s));
            if (aa == null) writeSize(z);
            for (int i = 0; i < z; i++) write(i < n ? b[i] : 0);
            return;
        }
        if (Number.class.isAssignableFrom(c)) {
            if (Long.class.isAssignableFrom(c)) {
                long v = o == null ? 0 : (long) o;
                XAUnsigned au = f == null ? null : f.getAnnotation(XAUnsigned.class);
                if (au == null) writeLong(v);
                else writeUnsginedInt(v);
                return;
            }
            if (Integer.class.isAssignableFrom(c)) {
                int v = o == null ? 0 : (int) o;
                XAUnsigned au = f == null ? null : f.getAnnotation(XAUnsigned.class);
                if (au == null) writeInt(v);
                else writeShort(v);
                return;
            }
            if (BigInteger.class.isAssignableFrom(c)) {
                writeUnsginedLong((BigInteger) o);
                return;
            }
            throw new IOException("Unsupported Number type: " + en(c, f));
        }
        if (Date.class.isAssignableFrom(c)) {
            writeDate((Date) o);
            return;
        }
        if (List.class.isAssignableFrom(c)) {
            if (f == null) throw new IOException("Only for Field Type: " + en(c, null));
            Class<?> s = XDataUtils.getParameterizedType(f);
            XABinary aa = f.getAnnotation(XABinary.class);
            @SuppressWarnings("unchecked")
            List<Object> l = (List<Object>) o;
            int n = l == null ? 0 : l.size();
            int z = aa == null ? n : aa.size();
            if (aa == null) writeSize(n);
            for (int i = 0; i < z; i++) writeObject(s, i < n ? l.get(i) : null, f, h);
        }
        if (XData.class.isAssignableFrom(c)) {
            writeFields(c, o);
            return;
        }
        if (c.isArray()) {
            Class<?> s = c.getComponentType();
            XABinary aa = f == null ? null : f.getAnnotation(XABinary.class);
            int n = o == null ? 0 : Array.getLength(o);
            int z = aa == null ? n : aa.size();
            if (aa == null) writeSize(n);
            for (int i = 0, j = h + 1; i < z; i++) writeObject(s, o == null ? null : Array.get(o, i), f, j);
            return;
        }
        throw new IOException("Unsupported Object type: " + en(c, f));
    }

    public void writeFields(Class<?> c, Object o) throws IOException {
        Class<?> s = c.getSuperclass();
        if (s != null && !s.isInterface()) writeFields(s, o);
        for (Field f : c.getDeclaredFields()) {
            if (!Modifier.isStatic(f.getModifiers())) {
                boolean b = !f.canAccess(o);
                if (b) f.setAccessible(true);
                Object fo;
                try {
                    fo = f.get(o);
                } catch (IllegalAccessException e) {
                    throw new IOException("Failed to access Field: " + en(s, f), e);
                }
                writeObject(f.getType(), fo, f, 0);
                if (b) f.setAccessible(false);
            }
        }
    }
}
