package com.example.demo;

import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;

@SpringBootApplication
@EnableAsync

@EnableScheduling
public class GestionEventApplication {
    @Autowired
    ClientService clientService;
    public static void main(String[] args) {

        SpringApplication.run(GestionEventApplication.class, args);
    }
    @Bean

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
                registry.addMapping("/**").allowedHeaders(" Origin"," Accept"," X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "autorisation ");
                registry.addMapping("/**").allowedOrigins("*");
                registry.addMapping("/**").exposedHeaders(" Access-Control-Allow-Origin"," Access-Control-Allow-Credentials"," autorisation ");

            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
