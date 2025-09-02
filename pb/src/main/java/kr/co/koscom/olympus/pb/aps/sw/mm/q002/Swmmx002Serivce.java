package kr.co.koscom.olympus.pb.aps.sw.mm.q002;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;

@Service("SWMMQ002")
public class Swmmx002Serivce {

	@Resource
	Swmmq002Mapper mapper;

	public void process(Swmmx002St st) {

		Swmmx002In in = st.getIn();

		PBCPage p0 = st.getPage();
		PBCPage p1 = p0.copy();

		//@formatter:off
		Swmmx002QryIn qryin = new Swmmx002QryIn()
				.setZIsuNo(in.getZIsuNo())
				.setZQrySrtDt(in.getZSrtDt())
				.setZQryEndDt(in.getZEndDt());
		//@formatter:on

		List<Swmmq002QryOut> qrylist = mapper.x002List(qryin, p0);

		PBCPage p2 = st.getPage();

		List<Swmmq002Out> list = new ArrayList<>(qrylist.size());

		for (Swmmq002QryOut q : qrylist) {
			Swmmq002Out r = new Swmmq002Out();
			r.zSrtDt = q.srtDt;
			r.zEndDt = q.endDt;
			r.dIntRat = q.acntIntRat;
			r.dCmsRat = q.cmsRat;
			list.add(r);
		}

		st.setOut(list);
	}

}
