package com.axisrooms.config;

import com.axisrooms.controller.customerController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
//import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
//@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket apiRepositories() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("apiRepositories")
                .select()
                .apis(or(
                        basePackage(com.axisrooms.controller.customerController.class.getPackage().getName())
                       // RequestHandlerSelectors.withClassAnnotation(customerController.class)
                ))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("api")
                .select()
                .apis(or(
                        RequestHandlerSelectors.withClassAnnotation(RestController.class)
                ))
                .paths(PathSelectors.any())
                .build();
    }


    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfo(
                "Chargebee API",
                "APIs using by AR CM to create Customers and Subscriptions",
                "API TOS",
                "Terms of service",
                apiContact().toString(),
                "License of API",
                "API license URL");
    }

    @Bean
    public Contact apiContact() {
        return new Contact(
                "Manasa",
                "Contact Url",
                "contact@example.com"
        );
    }
}
