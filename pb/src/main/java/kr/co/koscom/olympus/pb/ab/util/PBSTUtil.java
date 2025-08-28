package kr.co.koscom.olympus.pb.ab.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import kr.co.koscom.olympus.pb.apa.PBST;
import kr.co.koscom.olympus.pb.apa.PBService;

public class PBSTUtil {

	@SuppressWarnings("rawtypes")
	public static <T extends PBST> PBST createPbSt(String svcId) {

		PBService service = PBDataUtil.findPBService(svcId);
		if (service == null)
			throw new RuntimeException("Not found PBService for svcId(%s)".formatted(svcId));

		ParameterizedType p1 = PBDataUtil.findInterfaceParameterizedType(service.getClass(), PBService.class);
		if (p1 == null)
			throw new RuntimeException("Not found PBST class for svcId(%s)".formatted(svcId));

		Type t1 = p1.getActualTypeArguments()[0];
		PBST st = PBDataUtil.createObject(t1);

		return st;
	}

//	public static X createPbStIn(PBST st) {
//	
//		Type t2 = st.getClass().getGenericSuperclass();
//		ParameterizedType p2 = PBDataUtil.findInterfaceParameterizedType(t2, PBST.class);
//		if (p2 == null)
//			throw new RuntimeException("Not found IN,OUT class for svcId(%s)".formatted(svcId));
//
//		PBData in = PBDataUtil.createObject(p2.getActualTypeArguments()[0]);
//		PBData out = PBDataUtil.createObject(p2.getActualTypeArguments()[1]);
//
//		st.setIn(in);
//		st.setOut(out);
//
//		return (T) st;
//
//		
//		PBData in = PBDataUtil.createObject(p2.getActualTypeArguments()[0]);
//		PBData out = PBDataUtil.createObject(p2.getActualTypeArguments()[1]);		
//	}
}
