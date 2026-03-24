package com.eazyBank.Account.mapper;

import com.eazyBank.Account.dto.AccountsDto;
import com.eazyBank.Account.dto.CustomerDto;
import com.eazyBank.Account.entity.Accounts;
import com.eazyBank.Account.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {

    AccountsDto map(Accounts customer);

    Accounts unMap(AccountsDto customerDto);
}
