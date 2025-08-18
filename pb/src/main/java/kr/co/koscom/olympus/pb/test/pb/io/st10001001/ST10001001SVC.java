package kr.co.koscom.olympus.pb.test.pb.io.st10001001;

import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.db.service.impl.OnTblSpotOrdServiceImpl;
import kr.co.koscom.olympus.pb.include.PBService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static kr.co.koscom.olympus.pb.db.entity.table.OnTblSpotOrdTableDef.ON_TBL_SPOT_ORD;

@Service("PB_SID 10001001")
public class ST10001001SVC implements PBService<ST10001001> {

    @Resource
    OnTblSpotOrdServiceImpl service;

    @Override
    public int process(ST10001001 st) {
        QueryWrapper qw = new QueryWrapper();
        ST10001001In in = st.getIn();
        ST10001001Out out = st.getOut();
        if (StringUtils.isNotEmpty(in.getAcntNo())) qw.eq(ON_TBL_SPOT_ORD.ACNT_NO.getName(), in.getAcntNo());
        if (StringUtils.isNotEmpty(in.getIsuNo())) qw.eq(ON_TBL_SPOT_ORD.ISU_NO.getName(), in.getIsuNo());
        List<OnTblSpotOrd> ls = service.cpage(in.getPage());
        out.setList(ls);
        out.setPage(in.getPage());
        return 0;
    }
}
