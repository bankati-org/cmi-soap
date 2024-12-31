package com.bankati.cmi.Account.service;

import com.bankati.cmi.Account.model.Account;
import com.bankati.cmi.Account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;

    @Override
    public boolean AccountExist(String cin) {
        return accountRepository.existsAccountByOwnerCin(cin);
    }

    @Override
    public Account createAccount(Account account) {
        log.info(" creating the payment account for user with cin : {}",account.getOwnerId());
        if(AccountExist(account.getOwnerCin()))
            throw new RuntimeException("Account already exists for this client !!!");
        account.setBalance(0.0); // Initialiser le solde à 0
        account.setAccountNumber(UUID.randomUUID().toString());
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountDetails(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

    @Override
    public void updateBalance(String accountNumber, Double amount) {
        Account account = getAccountDetails(accountNumber);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }
}
