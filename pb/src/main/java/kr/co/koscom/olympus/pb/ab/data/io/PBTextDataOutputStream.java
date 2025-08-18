package kr.co.koscom.olympus.pb.ab.data.io;

import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import static kr.co.koscom.olympus.pb.ab.util.PBDataUtil.createObject;

public class PBTextDataOutputStream extends PBDataOutputStream {

    public PBTextDataOutputStream(OutputStream out) {
        super(out);
    }

    public PBTextDataOutputStream(OutputStream out, Charset charset) {
        super(out, charset);
    }

    @Override
    public void writeObject(Object o) throws IOException {
        PBDataT t = new PBDataT(null, o.getClass(), o, null, null);
        writeObject(t);
    }

    @Override
    public void writePBData(Class<?> c, Object o) throws IOException {
        PBDataT t = new PBDataT(null, c, o, null, null);
        writeFields(t);
    }

    public void writeString(Object o, byte[] b, int z, int s) throws IOException {
        if (b == null && o != null) b = String.valueOf(o).getBytes(charset);
        int n = b == null ? 0 : b.length;
        if (n > z) throw new IndexOutOfBoundsException("Scale = %d, Data = %d (%s)".formatted(z, n, o));
        if (s != 0) for (int i = 0, j = z - n; i < j; i++) write(' ');
        if (n > 0) write(b, 0, n);
        if (s == 0) for (int i = 0, j = z - n; i < j; i++) write(' ');
    }

    public void writeNumber(Object o, int z) throws IOException {
        writeString(o, null, z, 1);
    }

    public void writeObject(PBDataT t) throws IOException {
        if (PBData.class.isAssignableFrom(t.c)) {
            if (t.o == null) {
                if (t.c.isInterface()) return;
                t.o = createObject(t.c);
            }
            PBData x = (PBData) t.o;
            x.writePBData(this);
            return;
        }

        PBA a = t.a;
        if (a == null) throw new IOException("Field annotation required (@%s)".formatted(PBA.class.getSimpleName()));

        if (String.class.isAssignableFrom(t.c)) {
            if (a.fix()) {
                writeString(t.o, null, a.scale(), 0);
            } else {
                byte[] b = t.o == null ? null : t.o.toString().getBytes(charset);
                int n = b == null ? 0 : b.length;
                writeNumber(n, a.scale()); // 문자열 앞단에 문자열 길이 입력
                writeString(t.o, b, n, 0);
            }
            return;
        }
        if (t.c.isPrimitive()) {
            if (int.class.equals(t.c)) {
                writeNumber(String.valueOf(t.o), a.scale());
                return;
            }
            if (long.class.equals(t.c)) {
                writeNumber(String.valueOf(t.o), a.scale());
                return;
            }
            if (double.class.equals(t.c)) {
                writeNumber(String.valueOf(t.o), a.scale());
                return;
            }
            if (float.class.equals(t.c)) {
                writeNumber(String.valueOf(t.o), a.scale());
                return;
            }
            if (short.class.equals(t.c)) {
                writeNumber(String.valueOf(t.o), a.scale());
                return;
            }
            throw new IOException("Unexpected primitive type (%s, %s)".formatted(t.c.getCanonicalName(), t.o));
        }
        if (Number.class.isAssignableFrom(t.c)) {
            writeNumber(t.o, a.scale());
            return;
        }
        if (List.class.isAssignableFrom(t.c)) {
            Class<?> s = (Class<?>) ((ParameterizedType) t.f.getGenericType()).getActualTypeArguments()[0];
            List<?> l = (List<?>) t.o;
            int n = l == null ? 0 : l.size();
            int z = a.fix() ? a.scale() : n;
            if (a.fix()) {
                if (n > z) throw new IOException("ListSize(%d) is exceeded FixedSize(%d)".formatted(n, z));
            } else {
                writeNumber(n, a.scale());
            }
            for (int i = 0; i < z; i++) {
                Object x = i < n ? l.get(i) : null;
                PBDataT u = new PBDataT(t, x == null ? s : x.getClass(), null, t.f, null);
                writeObject(u);
            }
            return;
        }
        if (t.c.isArray()) {
            Class<?> s = t.c.getComponentType();
            int n = t.o == null ? 0 : Array.getLength(t.o);
            int z = a.fix() ? a.scale() : n;
            if (a.fix()) {
                if (n > z) throw new IOException("ArrayLength(%d) is exceeded FixedLength(%d)".formatted(n, z));
            } else {
                writeNumber(n, a.scale());
            }
            for (int i = 0; i < z; i++) {
                Object x = i < n ? Array.get(t.o, i) : null;
                PBDataT u = new PBDataT(t, x == null ? s : x.getClass(), null, t.f, null);
                writeObject(u);
            }
            return;
        }
        if (Date.class.isAssignableFrom(t.c)) {
            if (a.format().isEmpty())
                throw new IOException("Format required of @%s(format = ?)".formatted(PBA.class.getSimpleName()));
            String s = t.o == null ? null : DateFormatUtils.format((Date) t.o, a.format());
            writeString(s, null, a.scale(), 0);
        }
        throw new IOException("Unexpected type (%s)".formatted(t.c.getCanonicalName()));
    }

    public void writeFields(PBDataT t) throws IOException {
        Class<?> s = t.c.getSuperclass();
        if (s != null && !s.isInterface()) {
            PBDataT u = new PBDataT(t, s, t.o, t.f, t.a);
            writeFields(u);
        }
        for (Field f : t.c.getDeclaredFields()) {
            PBA a = f.getAnnotation(PBA.class);
            if (a == null || a.skip()) continue;
            if (t.o != null && !f.canAccess(t.o)) f.setAccessible(true);
            try {
                Object x = t.o == null ? null : f.get(t.o);
                Class<?> y = x == null ? f.getType() : x.getClass();
                PBDataT u = new PBDataT(t, y, x, f, a);
                writeObject(u);
            } catch (Throwable e) {
                throw new IOException("Failed to write field (%s.%s): %s".formatted(t.c.getCanonicalName(), f.getName(), e.getMessage()), e);
            }
        }
    }
}
