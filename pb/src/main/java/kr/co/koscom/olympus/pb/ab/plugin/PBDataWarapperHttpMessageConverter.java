package kr.co.koscom.olympus.pb.ab.plugin;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.util.PBDataUtil;
import kr.co.koscom.olympus.pb.apa.PBST;
import kr.co.koscom.olympus.pb.apa.hdr.PBDataWrapper;

public class PBDataWarapperHttpMessageConverter implements HttpMessageConverter<Object> {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Charset defaultCharset;
	
    public PBDataWarapperHttpMessageConverter() {
        this(StandardCharsets.UTF_8);
    }

    public PBDataWarapperHttpMessageConverter(Charset charset) {
		this.defaultCharset = charset;
	}

	@Override
    public boolean canRead(@NonNull Class<?> c, @Nullable MediaType t) {
        if (t != null && PBDataWrapper.class.isAssignableFrom(c)) for (MediaType a : getSupportedMediaTypes())
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
		if (charset == null) charset = defaultCharset;
		try {
			int z = (int) m.getHeaders().getContentLength();
			byte[] b = new byte[z];
			int n = m.getBody().read(b);
			if (n != z) throw new Exception( "Incorrect byte length between (cotent-length = %d, actual-length = %d)".formatted(z, n));
			String s = new String(b, charset);
			JSONObject o = new JSONObject(s);
			PBDataWrapper wo = objectMapper.convertValue(o, PBDataWrapper.class);
			wo.initService();
			PBData in = objectMapper.convertValue(wo.getIn(), wo.getCi());
			PBData out = PBDataUtil.createObject(wo.getCo());
			wo.getSt().setIn(in).setOut(out);
			return wo;
		} catch (Throwable e) {
			e.printStackTrace(System.err);
			throw new RuntimeException(e.getMessage(), e);
		}
    }

    @Override
    public void write(@NonNull Object o, @Nullable MediaType t, @NonNull HttpOutputMessage m) throws IOException, HttpMessageNotWritableException {
        Charset charset = t == null ? null : t.getCharset();
        if(charset == null) charset = defaultCharset;
        HttpHeaders h = m.getHeaders();
        if (h.getContentType() == null) h.setContentType(new MediaType(MediaType.APPLICATION_JSON, charset));
        PBST<?,?> st = o instanceof PBDataWrapper wo ? wo.getSt() : (PBST<?,?>) o;
        String s = objectMapper.writeValueAsString(st);
        byte[] b = s.getBytes(charset);
        // h.setContentLength(b.length);
        // st.getHdrCommon().setTgLen(b.length);
        m.getBody().write(b);
    }
}
