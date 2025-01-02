package com.bankati.cmi.account.service;

import com.bankati.cmi.account.ValidateCreateAccountRequest;
import com.bankati.cmi.account.ValidateCreateAccountResponse;
import com.bankati.cmi.account.model.Account;

public interface AccountService {
    boolean AccountExist(String cin,String ownerId);
    ValidateCreateAccountResponse createAccount(ValidateCreateAccountRequest ValidateCreateAccountRequest);
    Account getAccountDetails(String accountNumber);
    void updateBalance(String accountNumber, Double amount);
}