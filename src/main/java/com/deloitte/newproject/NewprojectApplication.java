package com.deloitte.newproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * This class is the main entry point of Spring boot application
 */
@Slf4j
@OpenAPIDefinition
@SpringBootApplication
@EnableCaching
public class NewprojectApplication {

	/**
	 * Main method that starts the application
	 * @param args arguments
	 */
	public static void main(String[] args) {

		log.info("Application Starting...");
		SpringApplication.run(NewprojectApplication.class, args);
		log.info("Application Started");

	}

}