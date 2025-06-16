package com.example.promo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class PromoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromoApplication.class, args);
	}

}
