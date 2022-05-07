package ru.mirea.ikbo1319.sidestory_server_part.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String imgsPath;

    @Value("C:/Users/Nastya/Desktop/practica_git/Sidestory_server_part — копия/src/main/resources/templates/game_pages/")
    private String gamePagesPath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/instruction").setViewName("instruction");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/usersImages/**")
                .addResourceLocations("file:" + imgsPath);
        registry.addResourceHandler("/templates/game_pages/**")
                .addResourceLocations("file:" + gamePagesPath);
    }

}
