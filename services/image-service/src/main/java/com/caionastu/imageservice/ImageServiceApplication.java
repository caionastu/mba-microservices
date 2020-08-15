package com.caionastu.imageservice;

import com.caionastu.core.advices.AdviceHandler;
import com.caionastu.imageservice.application.configuration.advices.ImageExceptionAdvices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImageServiceApplication {

	@Bean
	public AdviceHandler adviceHandler() {
		return new AdviceHandler();
	}

	@Bean
	public ImageExceptionAdvices exceptionAdvices() {
		return new ImageExceptionAdvices(adviceHandler());
	}

	public static void main(String[] args) {
		SpringApplication.run(ImageServiceApplication.class, args);
	}

}
