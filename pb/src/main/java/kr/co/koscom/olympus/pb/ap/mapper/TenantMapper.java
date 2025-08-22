package kr.co.koscom.olympus.pb.ap.mapper;

import kr.co.koscom.olympus.pb.ab.db.mapper.PBMapper;
import kr.co.koscom.olympus.pb.ap.entity.Tenant;
import org.apache.ibatis.annotations.Mapper;

/**
 * 映射层。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@Mapper
public interface TenantMapper extends PBMapper<Tenant> {

}
