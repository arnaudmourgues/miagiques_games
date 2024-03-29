package com.example.jo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class JeuxMiagiquesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeuxMiagiquesApplication.class, args);
	}

}
