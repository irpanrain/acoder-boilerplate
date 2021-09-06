package com.acoder.boilerplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class AcoderBoilerplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcoderBoilerplateApplication.class, args);
	}

}
