package kr.co.koscom.olympus.pb.ab.plugin;

import com.mybatisflex.core.tenant.TenantFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class PBTenantFactory implements TenantFactory {

    @Override
    public Object[] getTenantIds() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes == null) return new Object[0];
        String tenantId = (String) attributes.getAttribute("tenantId", RequestAttributes.SCOPE_REQUEST);
        if (tenantId == null) return new Object[0];
        return new Object[]{tenantId};
    }
}
