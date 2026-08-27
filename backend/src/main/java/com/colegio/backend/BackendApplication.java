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

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) {

		System.out.println();
		System.out.println("==============================================");
		System.out.println("   BACKEND GENERAL INICIADO CORRECTAMENTE");
		System.out.println("==============================================");
		System.out.printf("Puerto: %s%n", port);
		System.out.printf(
				"Swagger: http://localhost:%s/swagger-ui/index.html%n",
				port
		);
		System.out.printf(
				"OpenAPI: http://localhost:%s/v3/api-docs%n",
				port
		);
		System.out.println("==============================================");
		System.out.println();
	}

}
