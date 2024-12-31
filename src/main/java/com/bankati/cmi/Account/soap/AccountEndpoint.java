package com.bankati.cmi.Account.soap;

import com.bankati.cmi.Account.model.Account;
import com.bankati.cmi.Account.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AccountEndpoint {
    private static final String NAMESPACE_URI = "http://bankati.com/cmi/account";
    private final AccountServiceImpl accountService;

    @Autowired
    public AccountEndpoint(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createAccountRequest")
    @ResponsePayload
    public Account createAccount(@RequestPayload Account account) {
        return accountService.createAccount(account);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccountDetailsRequest")
    @ResponsePayload
    public Account getAccountDetails(@RequestPayload String accountNumber) {
        return accountService.getAccountDetails(accountNumber);
    }
}