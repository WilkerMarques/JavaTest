package com.example.sigabem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@SpringBootApplication
@EntityScan("com.example.sigabem.model")
public class SigaBemApplication {

	
	public class Configuration{
		@Bean
		public WebClient webClient(WebClient.Builder builder) {
			return builder
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
		}

		@Bean
		public ModelMapper modelMapper(){
			return new ModelMapper();
		}	
	}

	public static void main(String[] args) {
		SpringApplication.run(SigaBemApplication.class, args);
	}
}
