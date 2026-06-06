package com.eazyBank.Account.service.client;


import com.eazyBank.Account.dto.CardDto;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "card", fallback = CardFeignClientFallback.class)
public interface CardFeignClient {

    @GetMapping(value = "/api/v1/cards/fetch", consumes = "application/json")
    @Retry(name = "cardServiceRetry")
    ResponseEntity<CardDto> fetchCardDetailsByMobileNumber(@RequestParam String mobileNumber,
                                                           @RequestHeader(value = "eazybank-correlation-id") String correlationId);

}
