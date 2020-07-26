package com.caionastu.userservice.api;

import com.caionastu.core.advices.AdviceHandler;
import com.caionastu.userservice.api.application.configuration.advices.UserExceptionAdvices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class UserServiceApplication implements WebFluxConfigurer {

    @Bean
    public AdviceHandler adviceHandler() {
        return new AdviceHandler();
    }

    @Bean
    public UserExceptionAdvices exceptionAdvices() {
        return new UserExceptionAdvices(adviceHandler());
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
        log.info("User Service Started");
    }

}
