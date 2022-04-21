package com.wj.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfoBuilder())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wj.springcloud.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfoBuilder() {
        return new ApiInfoBuilder()
                .title("Knife4j-mic-order文档")
                .description("<div style='font-size:14px;color:red;'>swagger-bootstrap-ui-demo RESTful APIs</div>")
                .termsOfServiceUrl("服务url")
                .contact(new Contact("xxx", "url", "xxx@qq.com"))
                .version("1.0")
                .build();
    }
}