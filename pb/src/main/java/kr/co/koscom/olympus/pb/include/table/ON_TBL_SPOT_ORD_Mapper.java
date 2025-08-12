package kr.co.koscom.olympus.pb.include.table;

import kr.co.koscom.olympus.pb.include.PBMapper;
import kr.co.koscom.olympus.pb.on.bms.SPOT_ORD_TRX_SP_ST;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ON_TBL_SPOT_ORD_Mapper extends PBMapper<ON_TBL_SPOT_ORD> {

    void ON_FN_SPOT_ORD_MAIN(@Param("st") SPOT_ORD_TRX_SP_ST st);
}
