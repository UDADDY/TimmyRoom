package com.timmy.TimmyRoom.gloabl.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "TimmyRoom API Docs",
                description = "API",
                version = "v1"
        )
)
public class SwaggerConfig {
    private static final String BEARER_TOKEN_PREFIX = "Bearer";
    private static final String securityJwtName = "JWT";
    private static final String[] serverUrls = new String[]{"https://timmyroom.site", "http://localhost:8080"};

    @Bean
    public OpenAPI openAPI(){
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securityJwtName);
        Components components = new Components()
                .addSecuritySchemes(securityJwtName, new SecurityScheme()
                        .name(securityJwtName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(BEARER_TOKEN_PREFIX)
                        .bearerFormat(securityJwtName)
                );
        List<Server> servers = Arrays.stream(serverUrls).map((serverUrl) -> new Server().url(serverUrl)).toList();

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components)
                .servers(servers);
    }
}
