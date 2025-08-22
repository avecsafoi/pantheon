package kr.co.koscom.olympus.pb.ap.mapper;

import com.mybatisflex.annotation.UseDataSource;
import kr.co.koscom.olympus.pb.ab.db.mapper.PBMapper;
import kr.co.koscom.olympus.pb.ap.entity.Test001;
import org.apache.ibatis.annotations.Mapper;

/**
 * 映射层。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@Mapper
public interface Test001Mapper extends PBMapper<Test001> {

    @UseDataSource("ds1")
    int insert_db1(Test001 o);

    @UseDataSource("#last")
    int insert_db_last(Test001 o);
}
