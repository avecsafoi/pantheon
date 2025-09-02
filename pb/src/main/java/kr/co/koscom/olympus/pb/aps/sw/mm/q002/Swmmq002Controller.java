package kr.co.koscom.olympus.pb.aps.sw.mm.q002;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;

@Controller("pb/swmmq")
public class Swmmq002Controller {

	@Resource
	Swmmq002Serivce service1;
	
	@Resource
	Swmmx002Serivce service2;

	@PostMapping("Swmmq002")
	public @ResponseBody Swmmq002St q002List(@RequestBody Swmmq002St st) {
		service1.process(st);
		return st;
	}
	
	@PostMapping("Swmmq002")
	public @ResponseBody Swmmx002St q002List(@RequestBody Swmmx002St st) {
		service2.process(st);
		return st;
	}	

}
