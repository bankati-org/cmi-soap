package com.bankati.cmi.Transaction.mapper;

import com.bankati.cmi.Transaction.dto.TransactionDto;
import com.bankati.cmi.Transaction.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toDTO(Transaction transaction);
    @Mapping(target = "id",ignore = true)
    Transaction toEntity(TransactionDto transactionDto);
}
