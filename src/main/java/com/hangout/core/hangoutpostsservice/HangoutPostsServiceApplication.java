package com.hangout.core.hangoutpostsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HangoutPostsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HangoutPostsServiceApplication.class, args);
	}

}
