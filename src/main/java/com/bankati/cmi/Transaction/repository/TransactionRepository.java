package com.bankati.cmi.Transaction.repository;

import com.bankati.cmi.Transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findTransactionsByRecipientAccountIdOrSenderAccountId(String s, String d);
}
