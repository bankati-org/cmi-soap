package com.bankati.cmi.transaction.service;

import com.bankati.cmi.transaction.dto.TransactionDto;
import com.bankati.cmi.transaction.model.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionDto processTransaction(TransactionDto transaction);

    Transaction createTransaction(Transaction transaction);
    List<Transaction> getTransactionsByAccountId(Long accountId);
    List<Transaction> getTransactionsBySenderAccountNumber(String senderAccountNumber);
    List<Transaction> getTransactionsByRecipientAccountNumber(String recipientAccountNumber);
    Transaction getTransactionById(Long transactionId);
}

