package kr.co.koscom.olympus.pb.test.aa;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.apa.PBService;
import org.springframework.stereotype.Service;

@Service("11110000")
public class AAService implements PBService<AAST> {

    @Resource
    AAMapper mapper;

    @Override
    public void process(AAST st) {
        st.getOut().setList(mapper.list(st.getIn()));
    }
}
