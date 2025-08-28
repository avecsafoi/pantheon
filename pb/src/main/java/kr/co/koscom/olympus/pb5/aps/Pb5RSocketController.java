package kr.co.koscom.olympus.pb5.aps;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.rsocket.RSocket;
import kr.co.koscom.olympus.pb5.aph.Pb5Request;
import kr.co.koscom.olympus.pb5.aph.Pb5Response;
import kr.co.koscom.olympus.pb5.aph.Pb5Service;
import kr.co.koscom.olympus.pb5.au.Pb5ServiceUtil;
import lombok.Data;
import lombok.ToString;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class Pb5RSocketController {

	public class RClient {

		RSocketRequester rr;

		public RClient(RSocketRequester rr) {
			super();
			this.rr = rr;
		}
	}

	@Data
	@ToString
	public class PBMessage {
		private String username;
		private String message;
	}

	private final LinkedHashMap<RSocketRequester, RClient> rrs = new LinkedHashMap<>();

	@ConnectMapping
	public void onConnect(RSocketRequester rr) {
		RSocket r = rr.rsocket();
		if (r != null) {
			r.onClose().doFirst(() -> {
				rrs.put(rr, null);
			}).doOnError(e -> {
				e.printStackTrace(System.out);
			}).doFinally(_ -> {
				rrs.remove(rr);
			}).subscribe();
		}
	}

	@MessageMapping("message")
	Mono<Pb5Response<Object>> message(Pb5Request<Object> rq) throws Exception {
		String svcId = rq.getHeader().getSvcId();
		Pb5Service<Object, Object> service = Pb5ServiceUtil.findService(svcId);
		Class<?> ci = (Class<?>) ((ParameterizedType) service.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		ObjectMapper om = new ObjectMapper();
		Object oi = om.convertValue(rq.getData(), ci);
		rq.setData(oi);
		Pb5Response<Object> rs = service.process(rq);
		return Mono.just(rs);
	}

	@MessageMapping("send")
	void send(JSONObject o) {
		ObjectMapper om = new ObjectMapper();
		PBMessage m = om.convertValue(o, PBMessage.class);
		Flux.fromIterable(rrs.keySet()).doOnNext(c -> {
			c.route("xa", "xb").data(m).send().subscribe();
		}).subscribe();
	}
}
