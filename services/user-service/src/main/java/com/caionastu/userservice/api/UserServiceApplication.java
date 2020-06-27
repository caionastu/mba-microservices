package com.caionastu.userservice.api;

import com.caionastu.core.advices.ExceptionAdvices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class UserServiceApplication {

    @Bean
    public ExceptionAdvices exceptionAdvices() {
        return new ExceptionAdvices();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
        log.info("User Service Started");
    }

}
