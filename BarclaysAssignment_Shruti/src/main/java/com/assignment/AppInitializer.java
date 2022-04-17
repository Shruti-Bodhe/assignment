package com.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Shruti.Bodhe
 *
 */
@SpringBootApplication
@EnableScheduling
public class AppInitializer {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppInitializer.class, args);
	}

}
