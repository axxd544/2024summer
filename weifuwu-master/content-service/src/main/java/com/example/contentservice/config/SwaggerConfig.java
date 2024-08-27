package com.example.contentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import springfox.documentation.service.Contact;
@Configuration
@EnableSwagger2WebMvc

public class SwaggerConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("内容管理模块API展示")
                        .description("# 内容管理验证服务模块")
                        .contact(new Contact("TangSY", "https://www.imusic.com", "21241088@buaa.edu.cn"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("1.0版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.contentservice.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}