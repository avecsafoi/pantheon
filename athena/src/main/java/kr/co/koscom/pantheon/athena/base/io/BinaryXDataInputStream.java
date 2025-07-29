package kr.co.koscom.pantheon.athena.base.io;

import kr.co.koscom.pantheon.athena.base.io.data.annotations.XABinary;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAUnsigned;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static kr.co.koscom.pantheon.athena.base.io.XDataUtils.createObject;
import static kr.co.koscom.pantheon.athena.base.io.XDataUtils.en;

public class BinaryXDataInputStream extends DataInputStream {

    public final Charset charset;

    public BinaryXDataInputStream(InputStream in, Charset charset) {
        super(in);
        this.charset = charset;
    }

    public int readSize() throws IOException {
        return readUnsignedShort();
    }

    public long readUnsignedInt() throws IOException {
        return Integer.toUnsignedLong(readInt());
    }

    public BigInteger readUnsignedLong() throws IOException {
        return new BigInteger(Long.toUnsignedString(readLong()));
    }

    public String readString(int n) throws IOException {
        byte[] b = new byte[n];
        readFully(b, 0, n);
        return new String(b, charset);
    }

    public Date readDate() throws IOException {
        return new Date(readUnsignedInt());
    }

    public Object readObject(Class<?> c, Field f, int h) throws IOException {
        if (c.isPrimitive()) {
            if (int.class.isAssignableFrom(c)) {
                XAUnsigned au = f == null ? null : f.getAnnotation(XAUnsigned.class);
                return au == null ? readInt() : readUnsignedShort();
            }
            if (short.class.isAssignableFrom(c)) return readShort();
            if (long.class.isAssignableFrom(c)) {
                XAUnsigned au = f == null ? null : f.getAnnotation(XAUnsigned.class);
                return au == null ? readLong() : readUnsignedInt();
            }
            if (float.class.isAssignableFrom(c)) return readFloat();
            if (double.class.isAssignableFrom(c)) return readDouble();
            if (byte.class.isAssignableFrom(c)) return readByte();
            if (char.class.isAssignableFrom(c)) return (char) read();
            if (boolean.class.isAssignableFrom(c)) return readBoolean();
            throw new IOException("Unsupported primitive type: " + en(c, f));
        }
        if (String.class.isAssignableFrom(c)) {
            XABinary aa = f == null ? null : f.getAnnotation(XABinary.class);
            int z = aa == null ? readSize() : aa.size();
            return readString(z);
        }
        if (Number.class.isAssignableFrom(c)) {
            if (Long.class.isAssignableFrom(c)) {
                XAUnsigned au = f == null ? null : f.getAnnotation(XAUnsigned.class);
                if (au == null) return readLong();
                else return readUnsignedInt();
            }
            if (Integer.class.isAssignableFrom(c)) {
                XAUnsigned au = f == null ? null : f.getAnnotation(XAUnsigned.class);
                if (au == null) return readInt();
                else return readUnsignedShort();
            }
            if (BigInteger.class.isAssignableFrom(c)) return readUnsignedLong();
            throw new IOException("Unsupported Number type: " + en(c, f));
        }
        if (Date.class.isAssignableFrom(c)) {
            return readDate();
        }
        if (List.class.isAssignableFrom(c)) {
            if (f == null) throw new IOException("Only for Field Type: " + en(c, null));
            Class<?> s = XDataUtils.getParameterizedType(f);
            XABinary aa = f.getAnnotation(XABinary.class);
            int z = aa == null ? readSize() : aa.size();
            List<Object> l = new ArrayList<>(z);
            for (int i = 0, j = h + 1; i < z; i++) l.add(readObject(s, f, j));
            return l;
        }
        if (XData.class.isAssignableFrom(c)) {
            return readFields(c, createObject(c, f));
        }
        if (c.isArray()) {
            XABinary aa = f == null ? null : f.getAnnotation(XABinary.class);
            int z = aa == null ? readSize() : aa.size();
            Class<?> s = c.getComponentType();
            Object o = Array.newInstance(s, z);
            for (int i = 0, j = h + 1; i < z; i++) Array.set(o, i, readObject(s, f, j));
            return o;
        }
        throw new IOException("Unsupported Object type: " + en(c, f));
    }

    public Object readFields(Class<?> c, Object o) throws IOException {
        Class<?> s = c.getSuperclass();
        if (s != null && !s.isInterface()) readFields(s, o);
        for (Field f : c.getDeclaredFields()) {
            if (!Modifier.isStatic(f.getModifiers())) {
                if (!f.canAccess(o)) f.setAccessible(true);
                Object fo = readObject(f.getType(), f, 0);
                try {
                    f.set(o, fo);
                } catch (IllegalAccessException e) {
                    throw new IOException("Failed to access Field: " + en(s, f), e);
                }
            }
        }
        return o;
    }
}
