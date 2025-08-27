package kr.co.koscom.olympus.pb.ab.plugin;

import com.mybatisflex.core.datasource.DataSourceKey;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

public class PBTenantInterceptor implements HandlerInterceptor {

    protected static final List<String> headerList = List.of(
            "X-Forwarded-For",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_VIA",
            "IPV6_ADR");

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        // 요청을 통해 테넌트 ID를 가져옵니다.
        String tenantId = getTenantIdByReuqest(request);

        Random random = new Random();
        tenantId = random.nextInt() % 2 == 0 ? "코스콤" : "신정원";

        // 테넌트 ID를 요청 속성으로 설정합니다.
        request.setAttribute("tenantId", tenantId);

        DataSourceKey.use("코스콤".equals(tenantId) ? "pb1" : "pb2");

        return true;
    }

    public String getTenantIdByReuqest(@NonNull HttpServletRequest request) {
        String ip = null;
        for (String s : headerList)
            if (StringUtils.isNotBlank(ip = request.getHeader(s)) || !"unknown".equalsIgnoreCase(ip)) break;
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) ip = request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip) || "127.0.0.1".equals(ip)) try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return ip !=null && ip.matches("172.22.56.47") ? "코스콤" : "신정원";
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable Exception ex) throws Exception {
        // NOTHING TODO
    }
}
