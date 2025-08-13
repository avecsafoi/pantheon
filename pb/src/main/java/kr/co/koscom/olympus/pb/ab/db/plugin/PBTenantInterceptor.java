package kr.co.koscom.olympus.pb.ab.db.plugin;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class PBTenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) throws Exception {

        // 요청을 통해 테넌트 ID를 가져옵니다.
        String tenantId = null; // getTenantIdByReuqest(request);

        // 테넌트 ID를 요청 속성으로 설정합니다.
        request.setAttribute("tenantId", tenantId);

        return true;
    }
}
