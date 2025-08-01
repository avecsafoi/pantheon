package kr.co.koscom.olympus.athena.base.io.data;

import jakarta.annotation.Nonnull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class TextXDataHttpMessageConverter implements HttpMessageConverter<Object> {

    public final Charset defaultCharset;

    public TextXDataHttpMessageConverter() {
        this(StandardCharsets.UTF_8);
    }

    public TextXDataHttpMessageConverter(Charset defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    @Override
    public boolean canRead(@Nonnull Class<?> c, @Nullable MediaType t) {
        if (TextXData.class.isAssignableFrom(c))
            if (t == null) return true;
            else if (TextXData.class.isAssignableFrom(c))
                for (MediaType a : getSupportedMediaTypes())
                    if (t.isCompatibleWith(a)) return true;
        return false;
    }

    @Override
    public boolean canWrite(@Nonnull Class<?> c, @Nullable MediaType m) {
        return canRead(c, m);
    }

    @Override
    public @Nonnull List<MediaType> getSupportedMediaTypes() {
        return List.of(MediaType.TEXT_PLAIN);
    }

    @Override
    public @Nonnull List<MediaType> getSupportedMediaTypes(@Nonnull Class<?> clazz) {
        return canRead(clazz, null) ? getSupportedMediaTypes() : Collections.emptyList();
    }

    @Override
    public @Nonnull Object read(@Nonnull Class<?> c, HttpInputMessage m) throws IOException, HttpMessageNotReadableException {
        MediaType t = m.getHeaders().getContentType();
        Charset charset = t != null && t.getCharset() != null ? t.getCharset() : defaultCharset;
        TextXDataInputStream is = new TextXDataInputStream(m.getBody(), charset);
        return is.readObject(c);
    }

    @Override
    public void write(@Nonnull Object o, @Nullable MediaType t, HttpOutputMessage m) throws IOException, HttpMessageNotWritableException {
        Charset charset = t != null && t.getCharset() != null ? t.getCharset() : defaultCharset;
        HttpHeaders h = m.getHeaders();
        if (h.getContentType() == null)
            h.setContentType(new MediaType("text", "plain", charset));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TextXDataOutputStream tdos = new TextXDataOutputStream(baos, charset);
        tdos.writeObject(o);
        tdos.close();
        byte[] b = baos.toByteArray();
        h.setContentLength(b.length);
        OutputStream os = m.getBody();
        os.write(b);
        os.flush();
    }
}
