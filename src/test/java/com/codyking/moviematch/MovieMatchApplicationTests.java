package com.codyking.moviematch;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieMatchApplicationTests {

	@BeforeAll
	static void setUp() {
		// Loading environment variables from .env file.
		Dotenv dotenv = Dotenv.load();

		// Setting Spring Boot environment variables from .env.
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
	}


	@Test
	void contextLoads() {
	}

}
