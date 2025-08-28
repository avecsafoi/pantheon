package kr.co.koscom.olympus.pb5.aph;

import java.lang.reflect.ParameterizedType;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.olympus.pb5.au.Pb5ServiceUtil;

public class Pb5RequestWrapper extends Pb5Request<Object> {

	public void transfer() {
		Pb5Service<?, ?> service = Pb5ServiceUtil.findService(getHeader().getSvcId());
		Class<?> c = (Class<?>) ((ParameterizedType) service.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		ObjectMapper om = new ObjectMapper();
		Object o = om.convertValue(getData(), c);
		setData(o);
	}
}
