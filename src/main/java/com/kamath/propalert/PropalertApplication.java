package com.kamath.propalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy // This turns on our BrokerAspect!
public class PropalertApplication {

	public static void main(String[] args) {
		System.out.println("DEBUG: Connecting with " + System.getProperty("spring.datasource.username"));
		System.out.println("DEBUG: Connecting with " + System.getProperty("spring.datasource.password"));
		SpringApplication.run(PropalertApplication.class, args);
	}

}
