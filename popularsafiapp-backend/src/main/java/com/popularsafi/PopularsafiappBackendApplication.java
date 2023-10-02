package com.popularsafi;

import com.popularsafi.util.JwtAuthConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

///@ComponentScan(basePackages= {"controller"})
@SpringBootApplication
public class PopularsafiappBackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PopularsafiappBackendApplication.class, args);
    }

    public SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(PopularsafiappBackendApplication.class);
    }
/*
    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }*/
    @Bean
    public JwtAuthConverter jwtAuthConverter() {
        return new JwtAuthConverter();
    }
}
