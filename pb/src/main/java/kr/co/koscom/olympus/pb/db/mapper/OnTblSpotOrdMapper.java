package kr.co.koscom.olympus.pb.db.mapper;

import kr.co.koscom.olympus.pb.ab.db.mapper.PBMapper;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBNPage;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.on.bms.SPOT_ORD_TRX_SP_ST;
import kr.co.koscom.olympus.pb.test.pb.io.OrdQi;
import kr.co.koscom.olympus.pb.test.pb.io.OrdQiWithCPage;
import kr.co.koscom.olympus.pb.test.pb.io.st10001001.ST10001001In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 映射层。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@Mapper
public interface OnTblSpotOrdMapper extends PBMapper<OnTblSpotOrd> {

    void ON_FN_SPOT_ORD_MAIN(@Param("st") SPOT_ORD_TRX_SP_ST st);

    @Select("select * from on_tbl_spot_ord where ord_dt >= #{ordDt} LIMIT #{offset}, #{limit}")
    List<OnTblSpotOrd> page01(OrdQi qi);

    @Select("select * from on_tbl_spot_ord where ord_dt >= #{qi.ordDt} LIMIT #{qi.offset}, #{qi.limit}")
    List<OnTblSpotOrd> page02(@Param("qi") OrdQi qi);

    @Select("select * from on_tbl_spot_ord where ord_dt >= #{q1.ordDt} LIMIT #{q2.offset}, #{q2.limit}")
    List<OnTblSpotOrd> page03(OrdQi q1, OrdQi q2);

    @Select("select * from on_tbl_spot_ord where ord_dt >= #{qx1.ordDt} LIMIT #{qx2.offset}, #{qx2.limit}")
    List<OnTblSpotOrd> page04(OrdQi qx1, @Param("qx2") OrdQi q2);

    @Select("select * from on_tbl_spot_ord where ord_dt >= #{qi.ordDt}")
    List<OnTblSpotOrd> page40(OrdQi qi, PBNPage pg);

    @Select("""
<script>
    select * from on_tbl_spot_ord where 1 = 1
    <if test='qi.ordDt != null'>AND ord_dt >= #{qi.ordDt}</if>
    <if test='qi.ordNo != null and qi.ordNo > 0'>AND ord_no >= #{qi.ordno}</if>
</script>
    """)
    List<OnTblSpotOrd> page50(OrdQi qi, PBCPage pg);

    @Select("select * from on_tbl_spot_ord where ord_dt >= #{qi.ordDt}")
    List<OnTblSpotOrd> page51(PBCPage pg, OrdQi qi);

    @Select("select * from on_tbl_spot_ord where ord_dt >= #{ordDt}")
    List<OnTblSpotOrd> page52(OrdQiWithCPage qi);

    @Select("select * from on_tbl_spot_ord where ord_dt >= #{qi.ordDt}")
    List<OnTblSpotOrd> page53(@Param("qi") OrdQiWithCPage qi);

}
