package kr.co.koscom.olympus.pb.aps.sw.mm.q002;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;

@Service("SWMMQ002")
public class Swmmq002Serivce {

	@Resource
	Swmmq002Mapper mapper;

	public void process(Swmmq002St st) {

		Swmmq002In in = st.getIn();

		PBCPage p0 = in.getPage();
		PBCPage p1 = p0.copy();

		//@formatter:off
		Swmmq002QryIn qryin = new Swmmq002QryIn()
				.setZIsuNo(in.getZIsuNo())
				.setZQrySrtDt(in.getZSrtDt())
				.setZQryEndDt(in.getZEndDt())
				.setPage(in.getPage());
		//@formatter:on

		List<Swmmq002QryOut> qrylist = mapper.q002List(qryin);

		PBCPage p2 = in.getPage();

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
