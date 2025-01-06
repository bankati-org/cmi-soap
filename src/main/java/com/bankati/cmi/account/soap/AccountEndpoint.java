package com.bankati.cmi.account.soap;


import com.bankati.cmi.account.*;
import com.bankati.cmi.account.model.Account;
import com.bankati.cmi.account.service.AccountServiceImpl;
import com.bankati.cmi.account.service.recharge.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AccountEndpoint {
    private static final String NAMESPACE_URI = "http://www.bankati.com/cmi/account";
    private final AccountServiceImpl accountService;
    private final RechargeService rechargeService;
    @Autowired
    public AccountEndpoint(AccountServiceImpl accountService, RechargeService rechargeService) {
        this.accountService = accountService;
        this.rechargeService = rechargeService;
    }

    /**
     * Endpoint pour la requête ValidateCreateAccountRequest
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ValidateCreateAccountRequest")
    @ResponsePayload
    public ValidateCreateAccountResponse createAccount(
            @RequestPayload ValidateCreateAccountRequest validateCreateAccountRequest) {

        return accountService.createAccount(validateCreateAccountRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AccountRechargeRequest")
    @ResponsePayload
    public AccountRechargeResponse rechargeAccount(@RequestPayload AccountRechargeRequest request) {
        return rechargeService.processRecharge(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccountDetailsRequest")
    @ResponsePayload
    public GetAccountDetailsResponse getAccountDetails(@RequestPayload GetAccountDetailsRequest request) {
        Account account = accountService.getAccountDetails(request.getAccountNumber());
        GetAccountDetailsResponse response = new GetAccountDetailsResponse();
        com.bankati.cmi.account.Account account1 = new com.bankati.cmi.account.Account();
        account1.setAccountNumber(account.getAccountNumber());
        account1.setBalance(account.getBalance());
        account1.setId(account.getId());
        account1.setOwnerCin(account.getOwnerCin());
        response.setAccount(account1);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "WalletDepositRequest")
    @ResponsePayload
    public WalletDepositResponse depositToWallet(@RequestPayload WalletDepositRequest request) {
        return rechargeService.depositToWallet(request);
    }




}