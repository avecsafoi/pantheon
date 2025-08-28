package kr.co.koscom.olympus.pb.ab.plugin;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

import kr.co.koscom.olympus.pb.ab.data.PBData;

public class PBDataHttpMessageConverter extends AbstractGenericHttpMessageConverter<PBData> {

	@Override
	public @NonNull List<MediaType> getSupportedMediaTypes(@NonNull Class<?> c) {
		return supports(c) ? List.of(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN) : Collections.emptyList();
	}

	@Override
	protected boolean supports(@NonNull Class<?> c) {
		return PBData.class.isAssignableFrom(c);
	}

	@Override
	public boolean canRead(@NonNull Type type, @Nullable Class<?> ctrl, @Nullable MediaType mt) {
		if (mt != null && PBDataController.class.isAssignableFrom(ctrl)) {
			if (type instanceof ParameterizedType p) {
				
			} else if (type instanceof Class c) {
				
			}
		}
		return false;
	}

	@Override
	public boolean canWrite(@Nullable Type type, @NonNull Class<?> clazz, @Nullable MediaType mediaType) {
		// TODO Auto-generated method stub
		return super.canWrite(type, clazz, mediaType);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public @NonNull PBData read(Type type, @Nullable Class<?> contextClass, @NonNull HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void writeInternal(@NonNull PBData t, @Nullable Type type, @NonNull HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
	}

	@Override
	protected @NonNull PBData readInternal(@NonNull Class<? extends PBData> clazz, @NonNull HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		MediaType m = inputMessage.getHeaders().getContentType();
		Charset charset = m != null && m.getCharset() != null ? m.getCharset() : getDefaultCharset();
		StreamUtils.copyToString(inputMessage.getBody(), charset);		
		return (PBData) new Object();
	}

}
