package kr.co.koscom.olympus.pb.ab.conf;

import kr.co.koscom.olympus.pb.ab.plugin.PBTenantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PBWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (false) {
            registry.addInterceptor(new PBTenantInterceptor()).addPathPatterns("/**");
        }
    }
}