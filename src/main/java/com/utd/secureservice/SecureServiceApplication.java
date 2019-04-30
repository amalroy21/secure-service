package com.utd.secureservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.utd")
public class SecureServiceApplication {

	/**
	 * Represents logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(SecureServiceApplication.class);
	
	/**
	 * Represents the spring boot starter method
	 * 
	 * @param args
	 * @return {@link Void}
	 */
	public static void main(String[] args) {
		SpringApplication.run(SecureServiceApplication.class, args);
		logger.info("Secure Service started successfully");
	}
	
	/**
	 * Represents RestTemplate Bean
	 * @return A new instance of {@link RestTemplate}
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
