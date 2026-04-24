package com.eazyBank.Account.service;

import com.eazyBank.Account.dto.CustomerDetailsDto;

public interface ICustomerService {


    CustomerDetailsDto fetchCustomerDetailsByMobileNumber(String mobileNumber);

}
