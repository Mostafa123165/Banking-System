package com.eazyBank.Account.service.impl;

import com.eazyBank.Account.dto.CardDto;
import com.eazyBank.Account.dto.CustomerDetailsDto;
import com.eazyBank.Account.dto.CustomerDto;
import com.eazyBank.Account.dto.LoansDto;
import com.eazyBank.Account.entity.Accounts;
import com.eazyBank.Account.entity.Customer;
import com.eazyBank.Account.exception.ResourceNotFoundException;
import com.eazyBank.Account.mapper.AccountMapper;
import com.eazyBank.Account.mapper.CustomerMapper;
import com.eazyBank.Account.repository.AccountsRepository;
import com.eazyBank.Account.repository.CustomerRepository;
import com.eazyBank.Account.service.ICustomerService;
import com.eazyBank.Account.service.client.CardFeignClient;
import com.eazyBank.Account.service.client.LoanFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ICustomerServiceImpl implements ICustomerService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountMapper accountsMapper;
    private final CardFeignClient cardFeignClient;
    private final LoanFeignClient loanFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetailsByMobileNumber(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = customerMapper.map(customer);
        customerDto.setAccountsDto(accountsMapper.map(accounts));

        CardDto cardDto = cardFeignClient.fetchCardDetailsByMobileNumber(mobileNumber).getBody();
        LoansDto loansDto = loanFeignClient.fetchLoanDetailsByMobileNumber(mobileNumber).getBody();

        return new CustomerDetailsDto(
                customerDto,
                loansDto,
                cardDto );
    }
}
