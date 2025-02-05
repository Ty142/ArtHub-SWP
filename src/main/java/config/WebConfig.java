package config;

import security.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("", "/admin/**") // Chặn truy cập vào các trang cần đăng nhập
                .excludePathPatterns("/login", "/auth/login", "/public/**"); // Cho phép truy cập trang login
    }
}
