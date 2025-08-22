package kr.co.koscom.olympus.pb.test.aa;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AAMapper {

    @Select("select ord_dt as id, ord_mkt_code as name from on_tbl_spot_ord where ord_dt > #{id}")
    List<AAD> list(AAQ q);
}
