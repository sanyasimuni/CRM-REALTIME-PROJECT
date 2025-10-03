package com.apigateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

	@Bean
	RouteLocator routeLocator(RouteLocatorBuilder builder) {

		return builder.routes()
				.route("account-service", r -> r.path("/api/accounts/**").uri("lb://ACCOUNT-MICROSERVICE"))
				.route("user-contact-service", r -> r.path("/contact/**").uri("lb://USER-CONTACT-SERVICES"))
				.route("lead-service", r -> r.path("/api/leads/**").uri("lb://LEAD-MICROSERVICE")).build();
	}

}
