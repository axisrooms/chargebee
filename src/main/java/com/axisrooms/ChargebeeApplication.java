package com.axisrooms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ChargebeeApplication {

	public static void main(String[] args) {

		try {
			SpringApplication.run(ChargebeeApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
	}
}
