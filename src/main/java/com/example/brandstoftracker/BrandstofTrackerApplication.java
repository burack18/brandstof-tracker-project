package com.example.brandstoftracker;

import com.example.brandstoftracker.config.filter.JwtAuthorizationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BrandstofTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrandstofTrackerApplication.class, args);
    }
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
    @Bean
    public PasswordEncoder BcrypEncode(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtAuthorizationFilter jwtAuthenticationFilter() {
        return new JwtAuthorizationFilter();
    }
}
