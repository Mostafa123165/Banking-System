package com.eazyBank.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.time.Duration;

import static org.springframework.cloud.gateway.support.RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(p -> p.path("/eazyBank/accounts/**")
						.filters(f -> f.rewritePath("/eazyBank/accounts/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", String.valueOf(System.currentTimeMillis()))
								.circuitBreaker(coll -> coll.setName("accountCircuitBreaker"))
						)
						.uri("lb://ACCOUNT")
				)
				.route(p -> p.path("/eazyBank/cards/**")
						.filters(f -> f.rewritePath("/eazyBank/cards/(?<segment>.*)", "/${segment}")
								.retry(r -> r.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofSeconds(1), Duration.ofSeconds(10), 2,true))
						).uri("lb://CARD")
				).route(p -> p.path("/eazyBank/loans/**")
						.filters(f -> f.rewritePath("/eazyBank/loans/(?<segment>.*)", "/${segment}")
								.retry(r -> r.setRetries(3)
								.setMethods(HttpMethod.GET)
								.setSeries()
								.setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)
								.setBackoff(Duration.ofSeconds(1), Duration.ofSeconds(10), 2,true))
						)
						.uri("lb://LOAN")
				).build();
	}
}
