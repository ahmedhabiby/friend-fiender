package com.springboot.friendfinder.trackUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TrackingInterceptor trackingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(trackingInterceptor)
                .addPathPatterns("/**")   // تتبع APIs فقط
                .excludePathPatterns(
                        "/login",
                        "/signup",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                );
    }
}

