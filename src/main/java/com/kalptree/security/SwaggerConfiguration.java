package com.kalptree.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.OAS_30).apiInfo(getSwaggerInformation()).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo getSwaggerInformation() {
        return new ApiInfo("KalpTree", "KalpTree is a blogging website",
                "1.0", "Terms of service",
                new Contact("Kalp", "websiteUrl", "kalp.vora09@gmail.com"),
                "License of APIs", "License of Api Urls", Collections.emptyList());
    }
}
