package com.tang;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                //开启
                .enable(true)
                .select()
                //扫描路径下使用@Api的controller
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("接口文档test")
                .description("测试接口文档")
                //设置作者信息
                .contact(new Contact("tang","http://tang.com","xxxxxx9@qq.com"))
                .version("1.0")
                .build();
    }
}


