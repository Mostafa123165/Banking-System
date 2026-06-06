package com.eazyBank.Account.service.client;

import com.eazyBank.Account.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanFeignClientFallback implements LoanFeignClient{

    @Override
    public ResponseEntity<LoansDto> fetchLoanDetailsByMobileNumber(String mobileNumber, String correlationId) {
        return null;
    }
}
