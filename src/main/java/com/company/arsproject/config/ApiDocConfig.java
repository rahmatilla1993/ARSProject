package com.company.arsproject.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApiDocConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Airline Reservation System API")
                        .description("This Document Designed For Teaching SpringDOC Project")
                        .version("10")
                        .contact(new Contact()
                                .name("aka Gordon Freeman")
                                .email("freeman@gmail.com")
                                .url("https://github.com/rahmatilla1993"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .termsOfService("http://swagger.io/terms/"))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Production Server"),
                        new Server().url("http://localhost:9090").description("Test Server")
                )).addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components((new Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .name("basicAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                        )));
    }

    @Bean
    public GroupedOpenApi authGroupAPI() {
        return GroupedOpenApi.builder()
                .group("auth")
                .pathsToMatch("/api/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi agentGroupAPI() {
        return GroupedOpenApi.builder()
                .group("agent")
                .pathsToMatch("/api/flights/**", "/api/bookings/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminGroupAPI() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/api/admin/**", "/api/airport/**",
                        "/api/companies/**", "/api/bookings/**",
                        "/api/flights/all", "/api/flights/byCity")
                .build();
    }

    @Bean
    public GroupedOpenApi customerGroupAPI() {
        return GroupedOpenApi.builder()
                .group("customer")
                .pathsToMatch("/api/flights/all", "/api/flights/byCity", "/api/bookings/**")
                .build();
    }
}
