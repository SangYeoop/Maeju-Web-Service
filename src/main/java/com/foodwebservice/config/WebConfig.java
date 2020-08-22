package com.foodwebservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Profile("!test")
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${static.resource.location}")
    private String staticResourceLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations(staticResourceLocation);
    }
}
