package com.kamath.propalert;

// THESE ARE THE MISSING PIECES:
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PropalertApplication {

	public static void main(String[] args) {
		// Start the context
		ApplicationContext context = SpringApplication.run(PropalertApplication.class, args);

		// Debugging the DB connection we fixed
		Environment env = context.getBean(Environment.class);
		System.out.println("=========================================");
		System.out.println("RESOLVED USER: " + env.getProperty("spring.datasource.username"));
		System.out.println("RESOLVED URL : " + env.getProperty("spring.datasource.url"));
		System.out.println("=========================================");
	}
}