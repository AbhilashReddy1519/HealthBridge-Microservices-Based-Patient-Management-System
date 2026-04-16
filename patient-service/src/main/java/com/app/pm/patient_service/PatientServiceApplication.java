package com.app.pm.patient_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(WebServerApplicationContext context) {
		return args -> {
			int port = context.getWebServer().getPort();
			System.out.println("Hey Champ ^_~");
			System.out.println("Patient Service is running at ....");
			System.out.println("http://localhost:" + port);
		};
	}

}
