package com.vitalx.apizoo.config;

import com.vitalx.apizoo.dto.updateAnimalDTO;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final TypeResolver typeResolver;

    public SwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vitalx.apizoo"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .additionalModels(typeResolver.resolve(updateAnimalDTO.class))
                .useDefaultResponseMessages(false);
    }

    ApiInfo apiInfo() {
        return new ApiInfo(
                "Swagger AnimalsService Spring Boot v2.6.8",
                "This API is using to manage a Zoo and add all animals you want !",
                "1.0.0",
                "Terms of service",
                new Contact("Liam Macquaire", "https://github.com/Vitaalx", "liamdu92@gmail.com"),
                "License of API", "API license URL", Collections.emptyList()
        );
    }

}
