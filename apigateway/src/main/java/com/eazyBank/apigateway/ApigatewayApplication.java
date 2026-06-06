package com.eazyBank.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

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
						.filters(f -> f.rewritePath("/eazyBank/cards/(?<segment>.*)", "/${segment}"))
						.uri("lb://CARD")
				).route(p -> p.path("/eazyBank/loans/**")
						.filters(f -> f.rewritePath("/eazyBank/loans/(?<segment>.*)", "/${segment}")
								// per-route request timeout removed; apply global timeout via configuration or a GlobalFilter
						).metadata("timeout","2000")
						.uri("lb://LOAN")
				).build();
	}
}
