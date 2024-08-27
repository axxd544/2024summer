package com.example.consumerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 祝英台炸油条
 * @Time : 2022/6/7 17:08
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public CorsInterceptor corsInterceptor() {
        return new CorsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/doc.html/**",            // Knife4j 的主页面
                        "/swagger-resources/**",   // Knife4j 需要的资源
                        "/v2/api-docs/**",         // Swagger 的 API 文档路径
                        "/webjars/**"              // 静态资源
                );
    }

}