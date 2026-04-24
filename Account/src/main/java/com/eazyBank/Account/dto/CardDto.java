package com.eazyBank.Account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {

    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;

}
