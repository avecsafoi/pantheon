package kr.co.koscom.olympus.pb.db.service.impl;

import kr.co.koscom.olympus.pb.ab.db.service.PBServiceImpl;
import kr.co.koscom.olympus.pb.db.entity.Tenant;
import kr.co.koscom.olympus.pb.db.mapper.TenantMapper;
import kr.co.koscom.olympus.pb.db.service.TenantService;
import org.springframework.stereotype.Service;

/**
 * 服务层实现。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@Service
public class TenantServiceImpl extends PBServiceImpl<TenantMapper, Tenant> implements TenantService {

}
