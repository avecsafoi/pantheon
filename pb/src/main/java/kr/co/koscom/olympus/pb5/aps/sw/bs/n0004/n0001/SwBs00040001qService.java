package kr.co.koscom.olympus.pb5.aps.sw.bs.n0004.n0001;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb5.aph.Pb5Request;
import kr.co.koscom.olympus.pb5.aph.Pb5Response;
import kr.co.koscom.olympus.pb5.aph.Pb5Service;

public class SwBs00040001qService implements Pb5Service<SwBs00040001qIn, SwBs00040001qOut> {

	@Resource
	SwBs00040001qMapper mapper;
	
	@Override
	public Pb5Response<SwBs00040001qOut> process(Pb5Request<SwBs00040001qIn> request) {
		
		return null;
	}

}
