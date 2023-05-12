package com.hlh.usercloud.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


/**
 * @description:
 * @author: hlh
 * @date: 2022-11-28
 **/

@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
public class SwaggerConfiguration {


    @Bean(value = "UserApi")
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(groupApiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.hlh.usercloud.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder().title("智慧南工").description("<div style='font-size:15px;color:#e8322d;'>智慧南工微服务聚合文档</div>").termsOfServiceUrl("https://www.soft2176.com").contact(new Contact("软件2176", "https://soft2176.github.com", "soft2176@gmail.com")).version("1.0").build();
    }

}
