package com.example.ananas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Ánh xạ đường dẫn URL /images/** đến thư mục local D:/images
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///D:/Learn_Spring/Dat/Ananas/upload/")
                .setCachePeriod(3600); // Cache trong 1 giờ
    }
}
