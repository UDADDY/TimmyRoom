package com.timmy.TimmyRoom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "UPDATE", "DELETE", "PUT", "HEAD", "PATCH", "OPTIONS")
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}
