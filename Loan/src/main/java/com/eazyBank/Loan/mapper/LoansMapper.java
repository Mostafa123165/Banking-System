package com.eazyBank.Loan.mapper;

import com.eazyBank.Loan.dto.LoansDto;
import com.eazyBank.Loan.entity.Loans;
import org.mapstruct.Mapper;

@Mapper
public interface LoansMapper {

   LoansDto map(Loans loans);

   Loans unMap(LoansDto loans);

}
