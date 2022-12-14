package com.ortolegal;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableFeignClients
@EnableRabbit
@SecurityScheme(name = "Bearer", type = SecuritySchemeType.HTTP, bearerFormat = "jwt", scheme = "bearer")
@SecurityRequirement(name = "Bearer")
public class Ortolegal {

    public static void main(String[] args) {
        SpringApplication.run(Ortolegal.class, args);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
