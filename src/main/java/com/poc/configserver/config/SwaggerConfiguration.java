package com.poc.configserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicates;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).apiInfo(apiInfo()).select()
                .paths(Predicates.not(PathSelectors.regex("/error.*"))).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Config Server API's").description("Set properties through an API and save to database.")
                .contact(new Contact("Denis Silveira", "https://github.com/denissilveira/config-server-by-api", "denis.rayan@gmail.com")).version("0.0.1").build();
    }
}
