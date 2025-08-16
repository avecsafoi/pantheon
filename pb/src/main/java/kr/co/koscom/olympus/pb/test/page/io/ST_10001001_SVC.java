package kr.co.koscom.olympus.pb.test.page.io;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.db.service.impl.OnTblSpotOrdServiceImpl;
import kr.co.koscom.olympus.pb.include.PBService;
import org.springframework.stereotype.Service;

@Service("PB_SID 10001001")
public class ST_10001001_SVC implements PBService<ST_10001001> {

    @Resource
    OnTblSpotOrdServiceImpl service;

    @Override
    public int process(ST_10001001 st) {
        service.getMapper().cpage(st.getIn().getPage());
        return 0;
    }
}
