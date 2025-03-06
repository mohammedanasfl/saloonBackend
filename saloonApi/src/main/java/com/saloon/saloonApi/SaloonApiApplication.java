package com.saloon.saloonApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Saloon API", version = "1.0", description = "API for managing saloon products"))
@SpringBootApplication
public class SaloonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaloonApiApplication.class, args);
	}

}
