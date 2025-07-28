package kr.co.koscom.pantheon.athena.base.io;

import kr.co.koscom.pantheon.athena.base.io.data.annotations.KAText;
import org.apache.commons.lang3.StringUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.co.koscom.pantheon.athena.base.io.KDataUtils.en;
import static kr.co.koscom.pantheon.athena.base.io.KDataUtils.getAnnotationFields;

public class TextKDataOutputStream extends DataOutputStream {

    public static final Map<Class<?>, Field[]> FIELDS = new HashMap<>();

    private final Charset charset;

    public TextKDataOutputStream(OutputStream out, Charset charset) {
        super(out);
        this.charset = charset;
    }

    public void writeSize(int n, int size) throws IOException {
        String s = StringUtils.leftPad(String.valueOf(size), n, '0');
        byte[] b = s.getBytes(charset);
        write(b, 0, n);
    }

    public void writeObject(Class<?> c, Object o) throws IOException {
        writeFields(c, o);
    }

    public void writeObject(Class<?> c, Object o, Field f) throws IOException {
        KAText aa = f == null ? null : f.getAnnotation(KAText.class);
        if (aa == null)
            throw new IOException("Needed @%s for Field: %s".formatted(KAText.class.getSimpleName(), en(c, f)));
    }

    private void writeFields(Class<?> c, Object o) throws IOException {
        Field[] fa = FIELDS.get(c);
        if (fa == null) {
            List<Field> l = getAnnotationFields(c, KAText.class);
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
