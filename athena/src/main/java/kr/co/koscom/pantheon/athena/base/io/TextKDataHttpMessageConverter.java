package kr.co.koscom.pantheon.athena.base.io;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class TextKDataHttpMessageConverter implements HttpMessageConverter<Object> {
    // @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        if (mediaType != null)
            for (MediaType a : getSupportedMediaTypes())
                if (a.getType().equalsIgnoreCase(mediaType.getType()) && a.getSubtype().equalsIgnoreCase(mediaType.getSubtype()))
                    return true;
        return false;
    }

    // @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return canRead(clazz, mediaType);
    }

    // @Override
    public List<MediaType> getSupportedMediaTypes() {
        return List.of(MediaType.TEXT_PLAIN);
    }

    // @Override
    public Object read(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        MediaType mt = inputMessage.getHeaders().getContentType();
        assert mt != null;
        TextKDataInputStream is = new TextKDataInputStream(inputMessage.getBody(), mt.getCharset());
        return is.readObject(clazz);
    }

    // @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        for (MediaType a : getSupportedMediaTypes()) {
            for (MediaType b : outputMessage.getHeaders().getAccept()) {
                if (a.getType().equalsIgnoreCase(b.getType()) && a.getSubtype().equalsIgnoreCase(b.getSubtype())) {
                    Charset c = b.getCharset();
                    if (c != null) {
                        TextKDataOutputStream out = new TextKDataOutputStream(outputMessage.getBody(), c);
                        out.writeObject(o.getClass(), o);
                    }
                    return;
                }
            }
        }
        throw new HttpMessageNotWritableException("Not Found Matched MediaType in HttpOutputMessage.getHeaders().getAccept() for write");
    }
}
