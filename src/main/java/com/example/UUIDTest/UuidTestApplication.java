package com.example.UUIDTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJdbcRepositories
public class UuidTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UuidTestApplication.class, args);
	}

}
