package com.dk.promotional_engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PromotionalEngineApplication implements CommandLineRunner {

	private static Logger LOGGER = LoggerFactory.getLogger(PromotionalEngineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PromotionalEngineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		LOGGER.info("Promotion engine");

	}

}
