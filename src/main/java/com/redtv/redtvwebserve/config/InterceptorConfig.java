package com.redtv.redtvwebserve.config;

import com.redtv.redtvwebserve.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName InterceptorConfig
 * @Description   拦截器的配置
 * @Author admin
 * @Time 2023/2/20 16:58
 * @Version 1.0
 */


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;

    public InterceptorConfig(LogInterceptor logInterceptor) {
        this.logInterceptor = logInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);

    }
}
