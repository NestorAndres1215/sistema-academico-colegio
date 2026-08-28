package com.colegio.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BackendApplication implements CommandLineRunner {

	@Value("${server.port}")
	private String port;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) {

		String baseUrl = "http://localhost:" + port + contextPath;

		System.out.println();
		System.out.println("==================================================");
		System.out.println("       BACKEND GENERAL INICIADO CORRECTAMENTE");
		System.out.println("==================================================");
		System.out.printf("Puerto   : %s%n", port);
		System.out.printf("Backend  : %s%n", baseUrl);
		System.out.printf("Swagger  : %s/docs%n", baseUrl);
		System.out.printf("OpenAPI  : %s/v3/api-docs%n", baseUrl);
		System.out.println("==================================================");
		System.out.println();
	}
}