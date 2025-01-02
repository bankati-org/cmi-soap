package com.bankati.cmi.account.service;

import com.bankati.cmi.account.CreateAccountResponse;
import com.bankati.cmi.account.ValidateCreateAccountRequest;
import com.bankati.cmi.account.ValidateCreateAccountResponse;
import com.bankati.cmi.account.model.Account;
import com.bankati.cmi.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;

    @Override
    public boolean AccountExist(String cin,String ownerId) {
        return accountRepository.existsAccountByOwnerCinOrOwnerId(cin,ownerId);
    }

    @Override
    public ValidateCreateAccountResponse createAccount(ValidateCreateAccountRequest validateCreateAccountRequest) {
        String accountNumber;
        // Boucle pour générer un numéro unique
        do {
            accountNumber = generateRandom16Digits();
        } while (accountRepository.existsByAccountNumber(accountNumber)); // Vérifie si le numéro existe
        log.info(" creating the payment account for user with cin : {}", validateCreateAccountRequest.getCreateAccountRequest().getOwnerCin());
        ValidateCreateAccountResponse validateCreateAccountResponse = new ValidateCreateAccountResponse();
        CreateAccountResponse createAccountResponse = new CreateAccountResponse();
        if (AccountExist(validateCreateAccountRequest.getCreateAccountRequest().getOwnerCin(), validateCreateAccountRequest.getCreateAccountRequest().getOwnerId())) {
            validateCreateAccountResponse.getCreateAccountResponse().setMessage("Account already exists for this client !!!");
            validateCreateAccountResponse.getCreateAccountResponse().setStatus("FAILED");
            log.info(" message : {}", validateCreateAccountResponse.getCreateAccountResponse().getMessage());
            return validateCreateAccountResponse;
        }
        log.info("Account not existing for client with cin {}", validateCreateAccountRequest.getCreateAccountRequest().getOwnerCin());
        Account account = Account.builder()
                .accountNumber(accountNumber)
                .balance(0.0)
                .currency(validateCreateAccountRequest.getCreateAccountRequest().getCurrency())
                .ownerCin(validateCreateAccountRequest.getCreateAccountRequest().getOwnerCin())
                .ownerId(validateCreateAccountRequest.getCreateAccountRequest().getOwnerId())
                .build();
        try {
            accountRepository.save(account);
            createAccountResponse.setStatus("SUCCESS");
            createAccountResponse.setMessage("Account created successfully");
            validateCreateAccountResponse.setCreateAccountResponse(createAccountResponse);
            log.info(" message : {}", validateCreateAccountResponse.getCreateAccountResponse().getMessage());
        } catch (Exception e) {
            log.error("Error during account creation:", e);
            log.info(accountNumber);
            createAccountResponse.setStatus("FAILED");
            createAccountResponse.setMessage("Failed to create the account : " + e.getMessage());
            validateCreateAccountResponse.setCreateAccountResponse(createAccountResponse);

        }
        return validateCreateAccountResponse;
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

    public static String generateRandom16Digits() {
        Random random = new Random();
        long firstPart = (long) (Math.random() * 9_000_000_000_000_0000L) + 1_000_000_000_000_0000L;
        int secondPart = random.nextInt(10);
        return firstPart + String.valueOf(secondPart);
    }

}
