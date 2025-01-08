package com.bankati.cmi.transaction.mapper;

import com.bankati.cmi.transaction.dto.TransactionDto;
import com.bankati.cmi.transaction.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toDTO(Transaction transaction);
    @Mapping(target = "id",ignore = true)
    Transaction toCmiEntity(TransactionDto transactionDto);

    com.bankati.cmi.transaction.Transaction toCmiEntity(Transaction transaction);
}
