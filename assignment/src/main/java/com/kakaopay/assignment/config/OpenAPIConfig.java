package com.kakaopay.assignment.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("KakaoPay Assignment API Document")
                .version("v0.0.1")
                .description("KakaoPay Assignment API Document");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}