package com.kakaopay.assignment.config;

import com.kakaopay.assignment.interceptor.DataChangeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dataChangeInterceptor())
                .addPathPatterns("/*","/**")
                .excludePathPatterns("/change");
    }

    @Bean
    public DataChangeInterceptor dataChangeInterceptor() {
        return new DataChangeInterceptor();
    }



}
