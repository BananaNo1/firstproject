package com.myproject.firstproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author leis
 * @Date 2018/12/17 15:34
 * @Version 1.0
 **/

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${swagger2.host}")
    private String host;

    @Bean
    public Docket shopBackstageUser() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("用户模块")
                .host(host)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.myproject.firstproject.controller.portal"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("shop")
                .description("")
                .version("V1.0")
                .termsOfServiceUrl("")
                .contact(new Contact("shop", "http://51wustzds.com/", "545@qq.com"))
                .build();
    }
}
