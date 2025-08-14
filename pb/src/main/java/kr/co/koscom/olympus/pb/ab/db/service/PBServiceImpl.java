package kr.co.koscom.olympus.pb.ab.db.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import kr.co.koscom.olympus.pb.ab.db.mapper.PBMapper;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;

import java.util.List;

public class PBServiceImpl<M extends PBMapper<T>, T> extends ServiceImpl<M, T> {

    public List<T> cpage(PBCPage pg) {
        return getMapper().cpage(pg, null);
    }

    public List<T> cpage(PBCPage pg, QueryWrapper qw) {
        return getMapper().cpage(pg, qw);
    }
}
