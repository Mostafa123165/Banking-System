package com.eazyBank.Account.service.client;

import com.eazyBank.Account.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardFeignClientFallback implements CardFeignClient{
    @Override
    public ResponseEntity<CardDto> fetchCardDetailsByMobileNumber(String mobileNumber, String correlationId) {
        return null;
    }
}
