package com.yjy.read.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    // ctrl+o 查看可重写的方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**").excludePathPatterns(
                        "/login", "/css/*.css", "/scripts/*.js", "/images/*", "/font/*");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
