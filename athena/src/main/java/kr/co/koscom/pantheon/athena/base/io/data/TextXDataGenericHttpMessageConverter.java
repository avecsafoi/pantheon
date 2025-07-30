package kr.co.koscom.pantheon.athena.base.io.data;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    public @Nonnull Object read(@Nonnull Type type, @Nullable Class<?> contextClass, @Nonnull HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        if (type instanceof ParameterizedType p) {
            Class<?> c = (Class<?>) p.getRawType();
            if (List.class.isAssignableFrom(c)) {
                List<Object> l = new ArrayList<>();
                while (inputMessage.getBody().available() > 0)
                    l.add(super.read((Class<?>) p.getActualTypeArguments()[0], inputMessage));
                return l;
            }
            if (c.isArray()) {
                List<Object> l = new ArrayList<>();
                while (inputMessage.getBody().available() > 0)
                    l.add(super.read((Class<?>) p.getActualTypeArguments()[0], inputMessage));
                Object r = Array.newInstance((Class<?>) p.getRawType(), l.size());
                for (int i = 0; i < l.size(); i++) Array.set(r, i, l.get(i));
                return r;
            }
            throw new IOException("Unsupported Generic type: %s" + c.getName());
        }
        return super.read((Class<?>) type, inputMessage);
    }

    @Override
    public boolean canWrite(@Nullable Type type, @Nonnull Class<?> clazz, @Nullable MediaType mediaType) {
        return type != null && canRead(type, clazz, mediaType);
    }


    @Override
    public void write(@Nonnull Object o, @Nullable Type type, @Nullable MediaType contentType, @Nonnull HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (type instanceof ParameterizedType p) {
            Class<?> c = (Class<?>) p.getRawType();
            if (List.class.isAssignableFrom(c)) {
                @SuppressWarnings("unchecked")
                List<Object> l = (List<Object>) o;
                for (Object a : l) super.write(a, contentType, outputMessage);
                return;
            }
            if (c.isArray()) {
                int z = Array.getLength(o);
                for (int i = 0; i < z; i++) super.write(Array.get(o, i), contentType, outputMessage);
                return;
            }
            throw new IOException("Unsupported Generic type: %s" + c.getName());
        }
        super.write(o, contentType, outputMessage);
    }
}
