package kr.co.koscom.olympus.pb.ab.plugin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.data.io.PBTextDataOutputStream;
import kr.co.koscom.olympus.pb.apa.hdr.PBDataWrapper;

public class PBJsonDataHttpMessageConverter2 implements HttpMessageConverter<Object> {

	private final Charset defaultCharset;
	
    public PBJsonDataHttpMessageConverter2() {
        this(StandardCharsets.UTF_8);
    }

    public PBJsonDataHttpMessageConverter2(Charset charset) {
		this.defaultCharset = charset == null ? StandardCharsets.UTF_8 : charset;
	}

	@Override
    public boolean canRead(@NonNull Class<?> c, @Nullable MediaType t) {
        if (t != null && PBData.class.isAssignableFrom(c)) for (MediaType a : getSupportedMediaTypes())
            if (t.isCompatibleWith(a)) return true;
        return false;
    }

    @Override
    public boolean canWrite(@NonNull Class<?> c, @Nullable MediaType m) {
        return canRead(c, m);
    }

    @Override
    public @NonNull List<MediaType> getSupportedMediaTypes() {
        return List.of(MediaType.APPLICATION_JSON);
    }

    @Override
    public @NonNull List<MediaType> getSupportedMediaTypes(@NonNull Class<?> c) {
        boolean b = PBData.class.isAssignableFrom(c);
        return b ? getSupportedMediaTypes() : Collections.emptyList();
    }

    @Override
    public @NonNull Object read(@NonNull Class<?> c, @NonNull HttpInputMessage m) throws IOException, HttpMessageNotReadableException {
        MediaType t = m.getHeaders().getContentType();
        Charset charset = t == null ? null : t.getCharset();
        if(charset == null) charset =  defaultCharset;
		String s = StreamUtils.copyToString(m.getBody(), charset);
		try {
			JSONObject jo = new JSONObject(s);
			ObjectMapper om = new ObjectMapper();
			PBDataWrapper wr = om.convertValue(jo, PBDataWrapper.class);
			wr.processForJson();
			return wr;
		} catch (JSONException e) {
			throw new IOException(e);
		}
    }

    @Override
    public void write(@NonNull Object o, @Nullable MediaType t, @NonNull HttpOutputMessage m) throws IOException, HttpMessageNotWritableException {
        Charset charset = t == null ? null : t.getCharset(); 
        if(charset == null) charset = defaultCharset;
        HttpHeaders h = m.getHeaders();
        if (h.getContentType() == null) h.setContentType(new MediaType("text", "plain", charset));
        ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
        try (PBTextDataOutputStream tdos = new PBTextDataOutputStream(baos, charset)) {
            tdos.writeObject(o);
        } catch (Throwable e) {
            e.printStackTrace(System.err);
            throw new RuntimeException(e.getMessage(), e);
        }
        byte[] b = baos.toByteArray();
        h.setContentLength(b.length);
        OutputStream os = m.getBody();
        os.write(b);
        os.flush();
    }
}
