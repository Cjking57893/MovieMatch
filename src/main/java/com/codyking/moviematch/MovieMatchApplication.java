package com.codyking.moviematch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class MovieMatchApplication {

	public static void main(String[] args) {
		// Loading environment variables from .env file.
		Dotenv dotenv = Dotenv.load();

		// Setting Spring Boot environment variables from .env.
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(MovieMatchApplication.class, args);
	}

}
