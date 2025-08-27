package kr.co.koscom.olympus.pb.ab.conf;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.plugin.PBTenantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PBWebConfig implements WebMvcConfigurer {

    @Resource
    PBProperties pbProperties;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        if (pbProperties.isMultiTenantWebEnable()) {
            registry.addInterceptor(new PBTenantInterceptor()).addPathPatterns("/**");
        }
    }
}