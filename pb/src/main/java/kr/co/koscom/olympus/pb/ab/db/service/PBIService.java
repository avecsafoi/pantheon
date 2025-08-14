package kr.co.koscom.olympus.pb.ab.db.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;

import java.util.List;

public interface PBIService<T> extends IService<T> {

    List<T> cpage(PBCPage pg);

    List<T> cpage(PBCPage pg, QueryWrapper qw);
}
