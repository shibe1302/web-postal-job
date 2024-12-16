package com.shiba1302.timkiemvieclam.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCconfig implements WebMvcConfigurer {
    private static final String UPLOAD_DIR = "photos";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(UPLOAD_DIR, registry);
    }

    private void exposeDirectory(String uploadDir, ResourceHandlerRegistry registry) {
        Path path = Paths.get(uploadDir);

        registry.addResourceHandler("/" + uploadDir + "/**")
                .addResourceLocations("file:" + path.toAbsolutePath() + "/");

    }

}
