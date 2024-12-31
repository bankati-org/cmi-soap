package com.bankati.cmi.Account.service;

import com.bankati.cmi.Account.model.Account;

public interface AccountService {
    boolean AccountExist(String cin);
    Account createAccount(Account account);
    Account getAccountDetails(String accountNumber);
    void updateBalance(String accountNumber, Double amount);
}