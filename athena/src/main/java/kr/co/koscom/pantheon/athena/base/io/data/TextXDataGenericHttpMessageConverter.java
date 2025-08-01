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
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TextXDataGenericHttpMessageConverter extends TextXDataHttpMessageConverter implements GenericHttpMessageConverter<Object> {

    @Override
    public boolean canRead(@Nonnull Type type, @Nullable Class<?> contextClass, @Nullable MediaType mt) {
        switch (type) {
            case ParameterizedType pt -> {
                Type t = pt.getRawType();
                if (t instanceof Class<?> c) {
                    if (List.class.isAssignableFrom(c)) {
                        Type[] a = pt.getActualTypeArguments();
                        if (a.length == 1 && a[0] instanceof Class<?> x) {
                            return super.canRead(x, mt);
                        }
                    }
                }
            }
            case GenericArrayType at -> {
                if (at.getGenericComponentType() instanceof Class<?> c) {
                    return super.canRead(c, mt);
                }
            }
            case Class<?> c -> {
                if (c.isArray()) {
                    return super.canRead(c.getComponentType(), mt);
                } else {
                    return super.canRead(c, mt);
                }
            }
            default -> {

            }
        }
        return false;
    }

    @Override
    public @Nonnull Object read(@Nonnull Type type, @Nullable Class<?> contextClass, @Nonnull HttpInputMessage im) throws IOException, HttpMessageNotReadableException {
        MediaType mt = im.getHeaders().getContentType();
        Charset charset = mt != null && mt.getCharset() != null ? mt.getCharset() : defaultCharset;
        TextXDataInputStream tdis = new TextXDataInputStream(im.getBody(), charset);
        switch (type) {
            case ParameterizedType pt -> {
                Type t = pt.getRawType();
                if (t instanceof Class<?> c) {
                    if (List.class.isAssignableFrom(c)) {
                        Type[] a = pt.getActualTypeArguments();
                        if (a.length == 1 && a[0] instanceof Class<?> x) {
                            List<Object> list = new ArrayList<>();
                            while (tdis.available() > 0) {
                                Object o = tdis.readObject(x);
                                list.add(o);
                            }
                            return list;
                        }
                    }
                }
            }
            case GenericArrayType at -> {
                if (at.getGenericComponentType() instanceof Class<?> x) {
                    List<Object> list = new ArrayList<>();
                    while (tdis.available() > 0) {
                        Object o = tdis.readObject(x);
                        list.add(o);
                    }
                    Object ro = Array.newInstance(x, list.size());
                    for (int i = 0; i < list.size(); i++) Array.set(ro, i, list.get(i));
                    return ro;
                }
            }
            case Class<?> c -> {
                if (c.isArray()) {
                    List<Object> list = new ArrayList<>();
                    while (tdis.available() > 0) {
                        Object o = tdis.readObject(c.getComponentType());
                        list.add(o);
                    }
                    Object ro = Array.newInstance(c.getComponentType(), list.size());
                    for (int i = 0; i < list.size(); i++) Array.set(ro, i, list.get(i));
                    return ro;
                } else {
                    return tdis.readObject(c);
                }
            }
            default -> {

            }
        }
        throw new IOException("Unexpected Type " + type.getTypeName());
    }

    @Override
    public boolean canWrite(@Nullable Type type, @Nonnull Class<?> clazz, @Nullable MediaType mt) {
        return type != null && canRead(type, clazz, mt);
    }

    @Override
    public void write(@Nonnull Object o, @Nullable Type type, @Nullable MediaType mt, @Nonnull HttpOutputMessage om) throws IOException, HttpMessageNotWritableException {
        HttpHeaders h = om.getHeaders();
        if (mt == null) mt = h.getContentType();
        Charset charset = mt != null && mt.getCharset() != null ? mt.getCharset() : defaultCharset;
        if (h.getContentType() == null) h.setContentType(new MediaType("text", "plain", charset));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TextXDataOutputStream tdos = new TextXDataOutputStream(baos, charset);
        if (o instanceof List<?> l) {
            for (Object a : l) tdos.writeObject(a);
        } else if (o.getClass().isArray()) {
            int z = Array.getLength(o);
            for (int i = 0; i < z; i++) tdos.writeObject(Array.get(o, i));
        } else {
            tdos.writeObject(o);
        }
        tdos.close();
        byte[] b = baos.toByteArray();
        h.setContentLength(b.length);
        OutputStream os = om.getBody();
        os.write(b);
        os.flush();
    }
}
