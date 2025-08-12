package kr.co.koscom.olympus.pb.include.table;

import com.mybatisflex.annotation.UseDataSource;
import kr.co.koscom.olympus.pb.ab.db.PBMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
// @UseDataSource("ds1")
public interface PB_TBL_TEST_001_Mapper extends PBMapper<PB_TBL_TEST_001> {


    @UseDataSource("ds1")
    int insert_db1(PB_TBL_TEST_001 o);

    @UseDataSource("#last")
    int insert_db_last(PB_TBL_TEST_001 o);

}
