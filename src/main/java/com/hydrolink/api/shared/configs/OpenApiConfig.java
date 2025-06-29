package com.hydrolink.api.shared.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Demo - HydroLink API Backend",
                description = "This API provides endpoints for user authentication, monitoring and report",
                termsOfService = "https://hydrolink.example.com/terms_and_conditions",
                version = "1.0.0",
                license = @License(name = "Standard Software Use License for HydroLinkAPI", url = "https://hydrolink.example.com/license")
        ),
        servers = @Server(
                url = "https://inherent-steffi-hydrolink-531626a5.koyeb.app",
                description = "Koyeb Production Server"
        ),
        security = @SecurityRequirement(name = "Bearer Authentication")
)
@SecurityScheme(
        name = "Bearer Authentication",
        description = "JWT Bearer Token Authentication for accessing the API.",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {  }
