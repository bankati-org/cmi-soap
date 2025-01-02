package com.bankati.cmi.transaction.service;

import com.bankati.cmi.transaction.dto.TransactionDto;
import com.bankati.cmi.transaction.model.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionDto processTransaction(TransactionDto transaction);
    List<TransactionDto> getTransactions(String accountNumber);
}

