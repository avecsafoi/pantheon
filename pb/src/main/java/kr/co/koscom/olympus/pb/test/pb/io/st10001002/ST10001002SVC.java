package kr.co.koscom.olympus.pb.test.pb.io.st10001002;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ap.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.ap.mapper.OnTblSpotOrdMapper;
import kr.co.koscom.olympus.pb.apa.PBService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("10001002")
public class ST10001002SVC implements PBService<ST10001002> {

    @Resource
    OnTblSpotOrdMapper mapper;

    @Override
    public int process(ST10001002 st) {
        ST10001002In in = st.getIn();
        ST10001002Out out = st.getOut();
        List<OnTblSpotOrd> ls = mapper.page50(in.getOrdQi(), in.getPage());
        out.setList(ls);
        out.setPage(in.getPage());
        return 0;
    }
}
