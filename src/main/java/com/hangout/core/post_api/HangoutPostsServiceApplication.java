package com.hangout.core.post_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Hangout Post API", version = "v1", description = "This API handles requests to post content and comments in Hangout"), servers = @Server(url = "http://localhost:5013"))
public class HangoutPostsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(HangoutPostsServiceApplication.class, args);
	}

}
