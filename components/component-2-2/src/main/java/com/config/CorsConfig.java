package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                // Configuración para el endpoint principal de GraphQL
                registry.addMapping("/graphql")
                        .allowedOrigins("*") // Cambia esto por tu dominio frontend
                        .allowedMethods("POST", "OPTIONS", "GET", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);

                // Configuración para la interfaz de desarrollo GraphiQL
                registry.addMapping("/graphiql/**")
                        .allowedOrigins("*") // Permite acceso local para desarrollo
                        .allowedMethods("GET", "POST", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
