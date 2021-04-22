package com.antartyca.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.antartyca.proyecto.config.SwaggerConfig;

import io.swagger.annotations.Api;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@SpringBootApplication
@Import(SwaggerConfig.class)
public class FutbolApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FutbolApplication.class, args);
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
           registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
 
    }
}
