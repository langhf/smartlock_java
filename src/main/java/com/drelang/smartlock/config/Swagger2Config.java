package com.drelang.smartlock.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
  *  Swagger2 API 文档的配置
  * Created by Drelang on 2019/01/18
  */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable: true }")
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

    // TODO: 如何访问需要 jwtToken 的资源
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                            .apiInfo(apiInfo())
                            .select()
                            .apis(RequestHandlerSelectors.basePackage("com.drelang.smartlock.controller"))
                            .paths(PathSelectors.any())
                            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                        .title("智能门锁移动端API")
                        .description("给移动端提供的RESTful API")
                        .contact("tele: 13156533607")
                        .version("0.1.0")
                        .build();
    }
}
