package com.eazyBank.Account.mapper;

import com.eazyBank.Account.dto.CustomerDto;
import com.eazyBank.Account.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto map(Customer customer);

    Customer unMap(CustomerDto customerDto);
}
