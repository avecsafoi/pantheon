package kr.co.koscom.pantheon.athena.base.io;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TextXDataHttpMessageConverter implements HttpMessageConverter<TextXData> {

    private final Charset defaultCharset;

    public TextXDataHttpMessageConverter() {
        this(StandardCharsets.UTF_8);
    }

    public TextXDataHttpMessageConverter(Charset defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    @Override
    public boolean canRead(Class<?> c, @Nullable MediaType t) {
        if (TextXData.class.isAssignableFrom(c))
            if (t == null) return true;
            else if (TextXData.class.isAssignableFrom(c))
                for (MediaType a : getSupportedMediaTypes())
                    if (t.isCompatibleWith(a)) return true;
        return false;
    }

    @Override
    public boolean canWrite(Class<?> c, @Nullable MediaType m) {
        return canRead(c, m);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return List.of(MediaType.TEXT_PLAIN);
    }

    @Override
    public TextXData read(Class<? extends TextXData> c, HttpInputMessage m) throws IOException, HttpMessageNotReadableException {
        MediaType t = m.getHeaders().getContentType();
        Charset charset = t != null && t.getCharset() != null ? t.getCharset() : defaultCharset;
        TextXDataInputStream is = new TextXDataInputStream(m.getBody(), t.getCharset());
        TextXData o = is.readObject(c);
        StreamUtils.drain(is);
        return o;
    }

    @Override
    public void write(TextXData o, @Nullable MediaType t, HttpOutputMessage m) throws IOException, HttpMessageNotWritableException {
        Charset charset = t != null && t.getCharset() != null ? t.getCharset() : defaultCharset;
        TextXDataOutputStream os = new TextXDataOutputStream(m.getBody(), charset);
        os.writeObject(o);
        os.flush();
    }
}
