package com.eazyBank.Account.controller;

import com.eazyBank.Account.dto.CustomerDetailsDto;
import com.eazyBank.Account.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam String mobileNumber){
        return ResponseEntity.ok(customerService.fetchCustomerDetailsByMobileNumber(mobileNumber));
    }
}
