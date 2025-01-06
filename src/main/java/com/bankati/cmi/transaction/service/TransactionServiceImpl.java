package com.bankati.cmi.transaction.service;

import com.bankati.cmi.account.service.AccountService;
import com.bankati.cmi.exceptions.TransactionException;
import com.bankati.cmi.transaction.dto.TransactionDto;
import com.bankati.cmi.transaction.enums.TransactionStatus;
import com.bankati.cmi.transaction.mapper.TransactionMapper;
import com.bankati.cmi.transaction.model.Transaction;
import com.bankati.cmi.transaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.bankati.cmi.transaction.utils.DateTimeUtil.LocalDateTimetoString;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper transactionMapper;

    private final AccountService accountService;

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionDto processTransaction(TransactionDto transaction) {
        switch (transaction.getTransactionType()) {
            case "DEBIT" -> accountService.updateBalance(transaction.getSenderAccountId(), -transaction.getAmount());
            case "CREDIT" -> accountService.updateBalance(transaction.getSenderAccountId(), transaction.getAmount());
            case "TRANSFER" -> {
                accountService.updateBalance(transaction.getSenderAccountId(), -transaction.getAmount());
                accountService.updateBalance(transaction.getRecipientAccountId(), transaction.getAmount());
            }
        }
        transaction.setTransactionDate(LocalDateTimetoString(LocalDateTime.now()));
        return transactionMapper.toDTO(transactionRepository.save(transactionMapper.toEntity(transaction)));
    }

    @Override
    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.PENDING);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public List<Transaction> getTransactionsBySenderAccountNumber(String senderAccountNumber) {
        return transactionRepository.findBySenderAccountNumber(senderAccountNumber);
    }

    @Override
    public List<Transaction> getTransactionsByRecipientAccountNumber(String recipientAccountNumber) {
        return transactionRepository.findByRecipientAccountNumber(recipientAccountNumber);
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionException("Transaction not found"));
    }

}
