package com.eazyBank.Account.controller;

import com.eazyBank.Account.dto.CustomerDetailsDto;
import com.eazyBank.Account.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);


    private final ICustomerService customerService;

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader(value = "eazybank-correlation-id") String correlationId ,
                                                                   @RequestParam String mobileNumber){

        LOGGER.debug("Received request to fetch customer details for mobile number: {} , correlationId: {}", mobileNumber, correlationId);;
        return ResponseEntity.ok(customerService.fetchCustomerDetailsByMobileNumber(mobileNumber,correlationId));
    }
}
