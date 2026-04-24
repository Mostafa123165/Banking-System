package com.eazyBank.Account.service.client;


import com.eazyBank.Account.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loan")
public interface LoanFeignClient {

    @GetMapping(value = "/api/v1/loans/fetch",consumes = "application/json")
    ResponseEntity<LoansDto> fetchLoanDetailsByMobileNumber(@RequestParam String mobileNumber);

}
