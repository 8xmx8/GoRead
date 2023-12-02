package com.yjy.read.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class InterConfig {

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }
}
