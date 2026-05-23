package com.miniproject.be.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    private static final String SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement securityRequirement =
                new SecurityRequirement().addList(SCHEME_NAME);

        return new OpenAPI()
                .info(new Info()
                        .title("기분값 API")
                        .description("기분값 API 문서")
                        .version("v1"))
                .addSecurityItem(securityRequirement)
                .components(new Components()
                        .addSecuritySchemes(SCHEME_NAME, bearerScheme));
    }
}