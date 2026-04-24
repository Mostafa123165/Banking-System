package com.eazyBank.Account.service.client;


import com.eazyBank.Account.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("card")
public interface CardFeignClient {

    @GetMapping(value = "/api/v1/cards/fetch",consumes = "application/json")
    ResponseEntity<CardDto> fetchCardDetailsByMobileNumber(@RequestParam String mobileNumber);

}
