package com.truenews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"com.truenews.*", "com.truenews.config"})
@EnableCaching
public class TrueNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrueNewsApplication.class, args);
	}

}
