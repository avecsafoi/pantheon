package kr.co.koscom.olympus.pb.test.aa;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.include.PBService;
import org.springframework.stereotype.Service;

@Service("11110000")
public class AAService implements PBService<AAST> {

    @Resource
    AAMapper mapper;

    @Override
    public int process(AAST st) {
        st.getOut().setList(mapper.list(st.getIn()));
        return 0;
    }
}
