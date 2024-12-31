package com.bankati.cmi.Transaction.service;

import com.bankati.cmi.Account.service.AccountService;
import com.bankati.cmi.Transaction.dto.TransactionDto;
import com.bankati.cmi.Transaction.mapper.TransactionMapper;
import com.bankati.cmi.Transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.bankati.cmi.Transaction.utils.DateTimeUtil.LocalDateTimetoString;

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
    public List<TransactionDto> getTransactions(String accountNumber) {
        return transactionRepository.findTransactionsByRecipientAccountIdOrSenderAccountId(accountNumber, accountNumber)
                .stream().map(transactionMapper::toDTO).toList();
    }
}
