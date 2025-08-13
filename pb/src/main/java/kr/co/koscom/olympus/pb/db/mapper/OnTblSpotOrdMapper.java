package kr.co.koscom.olympus.pb.db.mapper;

import kr.co.koscom.olympus.pb.ab.db.mapper.PBMapper;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.on.bms.SPOT_ORD_TRX_SP_ST;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 映射层。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@Mapper
public interface OnTblSpotOrdMapper extends PBMapper<OnTblSpotOrd> {

    void ON_FN_SPOT_ORD_MAIN(@Param("st") SPOT_ORD_TRX_SP_ST st);
}
