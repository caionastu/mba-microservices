package com.caionastu.userservice.api;

import com.caionastu.core.advices.ExceptionAdvices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
