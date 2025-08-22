package kr.co.koscom.olympus.pb.test.aa;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AAMapper {

    @Select("select no1, no2, no3, nm1, nm2, nm3 from sw_mm_q001")
    List<AAD> list(AAQ q);
}
