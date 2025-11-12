//package com.example.demo.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.*;
//import io.swagger.v3.oas.models.security.*;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//
//        // 🔐 JWT Security Scheme Definition
//        SecurityScheme securityScheme = new SecurityScheme()
//                .name("Authorization")
//                .type(SecurityScheme.Type.HTTP)
//                .scheme("bearer")
//                .bearerFormat("JWT")
//                .description("Enter JWT token like: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...");
//
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization");
//
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Employee Management API")
//                        .version("1.0")
//                        .description("Spring Boot + PostgreSQL + JWT + Swagger Demo Project")
//                        .contact(new Contact()
//                                .name("Nagendra (bro 😎)")
//                                .email("nagendra@example.com")
//                                .url("https://github.com/your-profile"))
//                )
//                .servers(List.of(
//                        new Server().url("http://localhost:8080").description("Local Server")
//                ))
//                .addSecurityItem(securityRequirement)
//                .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("Authorization", securityScheme));
//    }
//}

//package com.example.demo.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public OpenAPI baseOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Employee Management API")
//                        .version("1.0.0")
//                        .description("Spring Boot + JWT + Swagger + PostgreSQL"));
//    }
//}
//

package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management API")
                        .version("1.0.0")
                        .description("Spring Boot + JWT + Swagger + PostgreSQL"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Enter your JWT token here")));
    }
}

