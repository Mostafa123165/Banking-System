package com.eazyBank.Account.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDetailsDto {

    private CustomerDto customer;
    private LoansDto loan;
    private CardDto card;
}
