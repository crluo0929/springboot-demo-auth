package com.example.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("後台API").version("1.0.0"))
				// Components section defines Security Scheme "Authorization"
				.components(new Components()
						.addSecuritySchemes("Authorization", new SecurityScheme()
								.type(SecurityScheme.Type.APIKEY)
								.in(SecurityScheme.In.HEADER)
								.name("Authorization")))
				// AddSecurityItem section applies created scheme globally
				.addSecurityItem(new SecurityRequirement().addList("Authorization"));
	}

}
