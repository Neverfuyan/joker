//package com.wj.springcloud.config;
//
//
//import io.swagger.models.auth.In;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.RequestParameterBuilder;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.schema.ScalarType;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.*;
//
///**
// * swagger 基本配置
// */
//@Configuration
//@EnableOpenApi
//public class SwaggerConfig {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.OAS_30).pathMapping("/")
//                // 定义是否开启swagger，false为关闭，可以通过变量控制
//                .enable(true)
//                // 将api的元信息设置为包含在json ResourceListing响应中。
//                .apiInfo(apiInfo())
//                // 接口调试地址
//                //.host(swaggerProperties.getTryHost())
//                // 选择哪些接口作为swagger的doc发布
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.wj"))
//                .paths(PathSelectors.any())
//                .build()
//                // 支持的通讯协议集合
//                .protocols(newHashSet("https", "http"))
//                // 授权信息设置，必要的header token等认证信息
//                .securitySchemes(securitySchemes())
//                // 授权信息全局应用
//                .securityContexts(securityContexts());
//    }
//
//    private List<RequestParameter> globalRequestParameters() {
//        RequestParameterBuilder parameterBuilder = new RequestParameterBuilder().in(ParameterType.HEADER).name("Token").required(false).query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
//        return Collections.singletonList(parameterBuilder.build());
//    }
//
//
//    /**
//     * 设置授权信息
//     */
//    private List<SecurityScheme> securitySchemes() {
//        ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
//        return Collections.singletonList(apiKey);
//    }
//
//
//    /**
//     * 授权信息全局应用
//     */
//    private List<SecurityContext> securityContexts() {
//        return Collections.singletonList(
//                SecurityContext.builder()
//                        .securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
//                        .build()
//        );
//    }
//
//    @SafeVarargs
//    private final <T> Set<T> newHashSet(T... ts) {
//        if (ts.length > 0) {
//            return new LinkedHashSet<>(Arrays.asList(ts));
//        }
//        return null;
//    }
//
//
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("资源服务API")
//                .description("资源服务接口文档")
//                .termsOfServiceUrl("localhost:2000")
//                .version("2.0")
//                .build();
//    }
//}
//
