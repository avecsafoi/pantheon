package kr.co.koscom.olympus.pb.ab.data.io;

import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

public class PBTextDataOutputStream extends PBDataOutputStream {

    public PBTextDataOutputStream(OutputStream out) {
        super(out);
    }

    public PBTextDataOutputStream(OutputStream out, Charset charset) {
        super(out, charset);
    }

    @Override
    public void writeObject(Object o) throws IOException {
        if (o != null) writeObject(o.getClass(), o, null);
    }

    private void writeString(Object o, byte[] b, int z, int s) throws IOException {
        if (b == null && o != null) b = String.valueOf(o).getBytes(charset);
        int n = b == null ? 0 : b.length;
        if (n > z) throw new IndexOutOfBoundsException("Scale = %d, Data = %d (%s)".formatted(z, n, o));
        if (s != 0) for (int i = 0, j = z - n; i < j; i++) write(' ');
        if (n > 0) write(b, 0, n);
        if (s == 0) for (int i = 0, j = z - n; i < j; i++) write(' ');
    }

    private void writeNumber(Object o, int z) throws IOException {
        writeString(o, null, z, 1);
    }

    private void writeObject(Class<?> c, Object o, Field f) throws IOException {

        if (PBData.class.isAssignableFrom(c)) {
            writeFields(c, o);
            return;
        }

        PBA a = f == null ? null : f.getAnnotation(PBA.class);
        if (a == null) throw new IOException("Field annotation required (@%s)".formatted(PBA.class.getSimpleName()));

        if (String.class.isAssignableFrom(c)) {
            if (a.fix()) {
                writeString(o, null, a.scale(), 0);
            } else {
                byte[] b = o == null ? null : o.toString().getBytes(charset);
                int n = b == null ? 0 : b.length;
                writeNumber(n, a.scale()); // 문자열 앞단에 문자열 길이 입력
                writeString(o, b, n, 0);
            }
            return;
        }
        if (c.isPrimitive()) {
            if (int.class.equals(c)) {
                writeNumber(String.valueOf(o), a.scale());
                return;
            }
            if (long.class.equals(c)) {
                writeNumber(String.valueOf(o), a.scale());
                return;
            }
            if (double.class.equals(c)) {
                writeNumber(String.valueOf(o), a.scale());
                return;
            }
            if (float.class.equals(c)) {
                writeNumber(String.valueOf(o), a.scale());
                return;
            }
            if (short.class.equals(c)) {
                writeNumber(String.valueOf(o), a.scale());
                return;
            }
            throw new IOException("Unexpected primitive type (%s, %s)".formatted(c.getCanonicalName(), o));
        }
        if (Number.class.isAssignableFrom(c)) {
            writeNumber(o, a.scale());
            return;
        }
        if (List.class.isAssignableFrom(c)) {
            Class<?> s = (Class<?>) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0];
            List<?> l = (List<?>) o;
            int n = l == null ? 0 : l.size();
            int z = a.fix() ? a.scale() : n;
            if (a.fix()) {
                if (n > z) throw new IOException("ListSize(%d) is exceeded FixedSize(%d)".formatted(n, z));
            } else {
                writeNumber(n, a.scale());
            }
            for (int i = 0; i < z; i++) writeObject(s, i < n ? l.get(i) : null, f);
            return;
        }
        if (c.isArray()) {
            int n = o == null ? 0 : Array.getLength(o);
            int z = a.fix() ? a.scale() : n;
            if (a.fix()) {
                if (n > z) throw new IOException("ArrayLength(%d) is exceeded FixedLength(%d)".formatted(n, z));
            } else {
                writeNumber(n, a.scale());
            }
            for (int i = 0; i < z; i++) writeObject(c.getComponentType(), i < n ? Array.get(o, i) : null, f);
            return;
        }
        if (Date.class.isAssignableFrom(c)) {
            if (a.format().isEmpty())
                throw new IOException("Format required of @%s(format = ?)".formatted(PBA.class.getSimpleName()));
            String s = o == null ? null : DateFormatUtils.format((Date) o, a.format());
            writeString(s, null, a.scale(), 0);
        }
        throw new IOException("Unexpected type (%s)".formatted(c.getCanonicalName()));
    }

    private void writeFields(Class<?> c, Object o) throws IOException {
        Class<?> s = c.getSuperclass();
        if (s != null && !s.isInterface()) writeFields(s, o);
        for (Field f : c.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) continue;
            if (o != null && !f.canAccess(o)) f.setAccessible(true);
            try {
                writeObject(f.getType(), o == null ? null : f.get(o), f);
            } catch (Throwable t) {
                throw new IOException("Failed to write field (%s.%s): %s".formatted(c.getCanonicalName(), f.getName(), t.getMessage()), t);
            }
        }
    }
}
