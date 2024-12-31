package com.bankati.cmi.Transaction.service;

import com.bankati.cmi.Transaction.dto.TransactionDto;
import com.bankati.cmi.Transaction.model.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionDto processTransaction(TransactionDto transaction);
    List<TransactionDto> getTransactions(String accountNumber);
}

