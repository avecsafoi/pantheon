package kr.co.koscom.olympus.pb5.au;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import kr.co.koscom.olympus.pb5.aph.Pb5Service;

public class Pb5ServiceUtil {

	private static final Map<String, Pb5Service<?, ?>> MAP = new HashMap<>(400);

	@SuppressWarnings("unchecked")
	public static <T extends Pb5Service<?, ?>> T findService(String svcId) {
		return (T) MAP.get(svcId);
	}

	public static Class<?> findServiceInClass(Pb5Service<?, ?> service) {
		return (Class<?>) ((ParameterizedType) service.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
