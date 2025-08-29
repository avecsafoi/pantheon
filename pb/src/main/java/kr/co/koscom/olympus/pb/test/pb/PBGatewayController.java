package kr.co.koscom.olympus.pb.test.pb;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.koscom.olympus.pb.apa.hdr.PBDataWrapper;

@RestController
@RequestMapping("/pb/gw")
public class PBGatewayController {

	@PostMapping(value = "json", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public @ResponseBody PBDataWrapper json(@RequestBody PBDataWrapper wo) throws Exception {
		wo.process();
		return wo;
	}
}
