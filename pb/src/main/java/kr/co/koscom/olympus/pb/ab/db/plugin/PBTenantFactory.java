package kr.co.koscom.olympus.pb.ab.db.plugin;

import com.mybatisflex.core.tenant.TenantFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class PBTenantFactory implements TenantFactory {

    public Object[] getTenantIds() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        String tenantId = (String) attributes.getAttribute("tenantId", RequestAttributes.SCOPE_REQUEST);
        return new Object[]{tenantId};
    }
}
