package com.app.notifyme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotifyMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifyMeApplication.class, args);
	}
}
