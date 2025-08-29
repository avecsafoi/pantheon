package kr.co.koscom.olympus.pb.apa.hdr;

import static kr.co.koscom.olympus.pb.ab.util.PBDataUtil.createObject;
import static kr.co.koscom.olympus.pb.ab.util.PBDataUtil.findInterfaceParameterizedType;
import static kr.co.koscom.olympus.pb.ab.util.PBDataUtil.findPBService;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.co.koscom.olympus.pb.ab.data.PBData;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataOutputStream;
import kr.co.koscom.olympus.pb.ab.util.PBDataUtil;
import kr.co.koscom.olympus.pb.apa.PBST;
import kr.co.koscom.olympus.pb.apa.PBSTObject;
import kr.co.koscom.olympus.pb.apa.PBService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Description("PB Data 송수신 객체")
public class PBDataWrapper extends PBSTObject<Object, Object> {

	@JsonIgnore
	@PBA(name = "서비스", skip = true)
	private transient PBService<PBST<?, ?>> service;

	@JsonIgnore
	@PBA(name = "입출력 객체", skip = true)
	private transient PBST<PBData, PBData> st;

	private Class<PBData> ci;
	private Class<PBData> co;

	public void process() throws IOException {
		service.process(st);
		hdrCommon = st.getHdrCommon();
		hdrAccount = st.getHdrAccount();
		in = st.getIn();
		out = st.getOut();
	}

	@SuppressWarnings("unchecked")
	public PBService<PBST<?, ?>> initService() throws IOException {
		if (service == null) {

			String svcId = hdrAccount.getSvcId();

			service = findPBService(svcId);
			if (service == null) throw new IOException("Not found PBService for svcId(%s)".formatted(svcId));

			ParameterizedType p1 = findInterfaceParameterizedType(service.getClass(), PBService.class);
			if (p1 == null) throw new IOException("Not found PBST class for svcId(%s)".formatted(svcId));

			st = createObject(p1.getActualTypeArguments()[0]);
			st.setHdrCommon(hdrCommon);
			st.setHdrAccount(hdrAccount);

			ParameterizedType p2 = findInterfaceParameterizedType(st.getClass().getGenericSuperclass(), PBST.class);
			if (p2 == null) throw new IOException("Not found IN,OUT class for svcId(%s)".formatted(svcId));

			ci = (Class<PBData>) p2.getActualTypeArguments()[0];
			co = (Class<PBData>) p2.getActualTypeArguments()[1];
		}
		return service;
	}

	@Override
	public void readPBData(PBDataInputStream is) throws IOException {
		hdrCommon = is.readObject(PBHdrCommon.class);
		hdrAccount = is.readObject(PBHdrAccount.class);
		initService();
		setIn(PBDataUtil.createObject(ci));
		setOut(PBDataUtil.createObject(co));
		st.getIn().readPBData(is);
		// Out 읽지 않음
	}

	@Override
	public void writePBData(PBDataOutputStream os) throws IOException {
		st.writePBData(os);
	}

	@Override
	public PBSTObject<Object, Object> setHdrAccount(PBHdrAccount hdrAccount) {
		if (st != null) st.setHdrAccount(hdrAccount);
		return super.setHdrAccount(hdrAccount);
	}

	@Override
	public PBSTObject<Object, Object> setHdrCommon(PBHdrCommon hdrCommon) {
		if (st != null) st.setHdrCommon(hdrCommon);
		return super.setHdrCommon(hdrCommon);
	}

	@Override
	public PBSTObject<Object, Object> setIn(Object in) {
		if (st != null) st.setIn((PBData) in);
		return super.setIn(in);
	}

	@Override
	public PBSTObject<Object, Object> setOut(Object out) {
		if (st != null) st.setOut((PBData) out);
		return super.setOut(out);
	}

}
