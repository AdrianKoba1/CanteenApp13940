package com.example.universityCanteenManagement;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
    public class Config implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry
                    .addMapping("/api/**")                    // only for your API endpoints
                    .allowedOrigins("http://localhost:3000")  // your React app
                    .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                    .allowCredentials(true);
        }
    }

