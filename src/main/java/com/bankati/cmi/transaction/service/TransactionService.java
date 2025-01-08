package com.bankati.cmi.transaction.service;

import com.bankati.cmi.transaction.ProcessTransactionRequest;
import com.bankati.cmi.transaction.ProcessTransactionResponse;
import com.bankati.cmi.transaction.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    ProcessTransactionResponse processTransaction(ProcessTransactionRequest processTransactionRequest);
    List<TransactionDto> getTransactions(String accountNumber);
}

