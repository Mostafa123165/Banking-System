package com.eazyBank.Account.service.impl;

import com.eazyBank.Account.dto.CustomerDto;
import com.eazyBank.Account.entity.Accounts;
import com.eazyBank.Account.entity.Customer;
import com.eazyBank.Account.exception.CustomerAlreadyExistsException;
import com.eazyBank.Account.exception.ResourceNotFoundException;
import com.eazyBank.Account.mapper.AccountMapper;
import com.eazyBank.Account.mapper.CustomerMapper;
import com.eazyBank.Account.repository.AccountsRepository;
import com.eazyBank.Account.repository.CustomerRepository;
import com.eazyBank.Account.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountMapper accountsMapper;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = customerMapper.unMap(customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    + customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType("SAVINGS");
        newAccount.setBranchAddress("ADDRESS");
        return newAccount;
    }


    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = customerMapper.map(customer);
        customerDto.setAccountsDto(accountsMapper.map(accounts));
        return customerDto;
    }


    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}
