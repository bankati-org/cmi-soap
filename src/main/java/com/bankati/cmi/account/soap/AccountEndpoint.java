package com.bankati.cmi.account.soap;


import com.bankati.cmi.account.GetAccountDetailsRequest;
import com.bankati.cmi.account.GetAccountDetailsResponse;
import com.bankati.cmi.account.ValidateCreateAccountRequest;
import com.bankati.cmi.account.ValidateCreateAccountResponse;
import com.bankati.cmi.account.model.Account;
import com.bankati.cmi.account.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AccountEndpoint {
    private static final String NAMESPACE_URI = "http://www.bankati.com/cmi/account";
    private final AccountServiceImpl accountService;

    @Autowired
    public AccountEndpoint(AccountServiceImpl accountService) {
        this.accountService = accountService;
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
        account1.setOwnerId(account.getOwnerId());
        response.setAccount(account1);
        return response;
    }




}