package com.bankati.cmi.transaction.service;

import com.bankati.cmi.account.model.Account;
import com.bankati.cmi.account.service.AccountService;
import com.bankati.cmi.transaction.ProcessTransactionRequest;
import com.bankati.cmi.transaction.ProcessTransactionResponse;
import com.bankati.cmi.transaction.dto.TransactionDto;
import com.bankati.cmi.transaction.enums.TransactionType;
import com.bankati.cmi.transaction.mapper.TransactionMapper;
import com.bankati.cmi.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.bankati.cmi.transaction.Transaction;

import static com.bankati.cmi.utils.ValidationUtils.validatePayment;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper transactionMapper;

    private final AccountService accountService;

    private final TransactionRepository transactionRepository;

    @Override
    public ProcessTransactionResponse processTransaction(ProcessTransactionRequest processTransactionRequest) {
        Transaction transaction = processTransactionRequest.getTransaction();
        validateTransaction(transaction);

        switch (transaction.getTransactionType()) {
            case "DEBIT" -> accountService.updateBalance(transaction.getRecipientAccountNumber(), -transaction.getAmount());
            case "CREDIT" -> accountService.updateBalance(transaction.getRecipientAccountNumber(), transaction.getAmount());
            case "TRANSFER" -> {
                accountService.updateBalance(transaction.getSenderAccountNumber(), -transaction.getAmount());
                accountService.updateBalance(transaction.getRecipientAccountNumber(), transaction.getAmount());
            }
            default -> throw new IllegalArgumentException("Unsupported transaction type: " + transaction.getTransactionType());
        }

        com.bankati.cmi.transaction.model.Transaction cmiTransaction = new com.bankati.cmi.transaction.model.Transaction();
        Account senderAccount = accountService.getAccountDetails(transaction.getSenderAccountNumber());
        Account recipientAccount = accountService.getAccountDetails(transaction.getRecipientAccountNumber());
        validatePayment(senderAccount,recipientAccount,transaction);
        cmiTransaction.setAccountId(accountService.getAccountDetails(transaction.getSenderAccountNumber()).getId());
        cmiTransaction.setAmount(transaction.getAmount());
        cmiTransaction.setCurrency(transaction.getCurrency());
        cmiTransaction.setDescription(transaction.getDescription());
        cmiTransaction.setSenderAccountNumber(transaction.getSenderAccountNumber());
        cmiTransaction.setRecipientAccountNumber(transaction.getRecipientAccountNumber());
        cmiTransaction.setStatus(com.bankati.cmi.transaction.enums.TransactionStatus.PENDING);
        cmiTransaction.setTransactionType(transaction.getTransactionType());
        cmiTransaction.setTransactionDate(LocalDateTime.now());
        ProcessTransactionResponse processTransactionResponse = new ProcessTransactionResponse();
        processTransactionResponse.setTransaction(transactionMapper.toCmiEntity(transactionRepository.save(cmiTransaction)));
        return processTransactionResponse;
    }

    private void validateTransaction(Transaction transaction) {
        if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero.");
        }
        if (transaction.getSenderAccountNumber().isEmpty()) {
            throw new IllegalArgumentException("Sender account ID must not be null or empty.");
        }
        if (TransactionType.valueOf(transaction.getTransactionType()) == TransactionType.TRANSFER && transaction.getRecipientAccountNumber().isEmpty()) {
            throw new IllegalArgumentException("Recipient account ID must not be null or empty for transfers.");
        }
    }


    @Override
    public List<TransactionDto> getTransactions(String accountNumber) {
        return transactionRepository.findTransactionsByRecipientAccountNumberOrSenderAccountNumber(accountNumber, accountNumber)
                .stream().map(transactionMapper::toDTO).toList();
    }
}
