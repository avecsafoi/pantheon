package kr.co.koscom.pantheon.athena.base.io.data;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextXDataGenericHttpMessageConverter extends TextXDataHttpMessageConverter implements GenericHttpMessageConverter<Object> {

    @Override
    public boolean canRead(@Nonnull Type type, @Nullable Class<?> contextClass, @Nullable MediaType mediaType) {
        if (type instanceof ParameterizedType p) {
            for (Type t : p.getActualTypeArguments())
                if (super.canRead((Class<?>) t, mediaType)) return true;
            return super.canRead((Class<?>) p.getRawType(), mediaType);
        }
        return false;
    }

    @Override
    public @Nonnull Object read(@Nonnull Type type, @Nullable Class<?> contextClass, @Nonnull HttpInputMessage m) throws IOException, HttpMessageNotReadableException {
        MediaType t = m.getHeaders().getContentType();
        Charset charset = t != null && t.getCharset() != null ? t.getCharset() : defaultCharset;
        TextXDataInputStream tdis = new TextXDataInputStream(m.getBody(), charset);
        if (type instanceof ParameterizedType p) {
            Class<?> c = (Class<?>) p.getRawType();
            if (List.class.isAssignableFrom(c)) {
                Class<?> s = (Class<?>) p.getActualTypeArguments()[0];
                List<Object> l = new ArrayList<>();
                while (tdis.available() > 0) l.add(tdis.readObject(s));
                return l;
            }
            if (Map.class.isAssignableFrom(c)) {
                Class<?> a = (Class<?>) p.getActualTypeArguments()[0];
                Class<?> b = (Class<?>) p.getActualTypeArguments()[1];
                HashMap<Object, Object> h = new HashMap<>();
                while (tdis.available() > 0) h.put(tdis.readObject(a), tdis.readObject(b));
                return h;
            }
            if (c.isArray()) {
                Class<?> s = (Class<?>) p.getActualTypeArguments()[0];
                List<Object> l = new ArrayList<>();
                while (tdis.available() > 0) l.add(tdis.readObject(s));
                Object a = Array.newInstance(s, l.size());
                for (int i = 0; i < l.size(); i++) Array.set(a, i, l.get(i));
                return a;
            }
            throw new IOException("Unsupported Generic type: %s" + c.getName());
        }
        return tdis.readObject((Class<?>) type);
    }

    @Override
    public boolean canWrite(@Nullable Type type, @Nonnull Class<?> clazz, @Nullable MediaType mediaType) {
        return type != null && canRead(type, clazz, mediaType);
    }


    @Override
    public void write(@Nonnull Object o, @Nullable Type type, @Nullable MediaType t, @Nonnull HttpOutputMessage m) throws IOException, HttpMessageNotWritableException {
        Charset charset = t != null && t.getCharset() != null ? t.getCharset() : defaultCharset;
        HttpHeaders h = m.getHeaders();
        if (h.getContentType() == null)
            h.setContentType(new MediaType("text", "plain", charset));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TextXDataOutputStream tdos = new TextXDataOutputStream(baos, charset);
        if (type instanceof ParameterizedType p) {
            Class<?> c = (Class<?>) p.getRawType();
            if (o instanceof List<?> l) {
                for (Object a : l) tdos.writeObject(a);
            } else if (o instanceof Map<?, ?> x) {
                for (Map.Entry<?, ?> e : x.entrySet()) {
                    tdos.writeObject(e.getKey());
                    tdos.writeObject(e.getValue());
                }
            } else if (c.isArray()) {
                int z = Array.getLength(o);
                for (int i = 0; i < z; i++) tdos.writeObject(Array.get(o, i));
            } else {
                throw new IOException("Unsupported Generic type: %s" + c.getName());
            }
        } else {
            tdos.writeObject(o);
        }
        tdos.close();
        byte[] b = baos.toByteArray();
        h.setContentLength(b.length);
        OutputStream os = m.getBody();
        os.write(b);
        os.flush();
    }
}
