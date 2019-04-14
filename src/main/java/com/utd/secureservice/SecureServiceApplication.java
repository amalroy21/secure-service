package com.utd.secureservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

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

}
