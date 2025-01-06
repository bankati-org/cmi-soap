package com.bankati.cmi.account.service;

import com.bankati.cmi.account.CreateAccountResponse;
import com.bankati.cmi.account.ValidateCreateAccountRequest;
import com.bankati.cmi.account.ValidateCreateAccountResponse;
import com.bankati.cmi.account.model.Account;
import com.bankati.cmi.account.repository.AccountRepository;
import com.bankati.cmi.adapter.WalletAdapter;
import com.bankati.cmi.dto.CreateWalletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final WalletAdapter walletAdapter;
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;

    @Override
    public boolean AccountExist(String cin) {
        return accountRepository.existsAccountByOwnerCin(cin);
    }

    @Override
    public ValidateCreateAccountResponse createAccount(ValidateCreateAccountRequest validateCreateAccountRequest) {
        String accountNumber;
        do {
            accountNumber = generateRandom16Digits();
        } while (accountRepository.existsByAccountNumber(accountNumber)); // Vérifie si le numéro existe
        log.info(" creating the payment account for user with cin : {}", validateCreateAccountRequest.getCreateAccountRequest().getOwnerCin());
        ValidateCreateAccountResponse validateCreateAccountResponse = new ValidateCreateAccountResponse();
        CreateAccountResponse createAccountResponse = new CreateAccountResponse();
        if (AccountExist(validateCreateAccountRequest.getCreateAccountRequest().getOwnerCin())) {
            createAccountResponse.setMessage("Account already exists for this client !!!");
            createAccountResponse.setStatus("FAILED");
            validateCreateAccountResponse.setCreateAccountResponse(createAccountResponse);
            log.info(" message : {}", validateCreateAccountResponse.getCreateAccountResponse().getMessage());
            return validateCreateAccountResponse;
        }
        log.info("Account not existing for client with cin {}", validateCreateAccountRequest.getCreateAccountRequest().getOwnerCin());
        Account account = Account.builder()
                .accountNumber(accountNumber)
                .balance(0.0)
                .createdAt(new Date())
                .currency(validateCreateAccountRequest.getCreateAccountRequest().getCurrency())
                .ownerCin(validateCreateAccountRequest.getCreateAccountRequest().getOwnerCin())
                .build();
        try {
            Account account1 = accountRepository.save(account);
            walletAdapter.createWallet(
                    CreateWalletRequest.builder()
                            .userId(account1.getId())
                            .defaultCurrency(account1.getCurrency())
                            .build());
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
        long firstPart = (long) (Math.random() * 9_000_000_000_000_000L) + 1_000_000_000_000_000L;
        return String.valueOf(firstPart);
    }


    @Transactional
    @Override
    public Account updateAccount(String ownerCin, Double amount) {
        Account account = accountRepository.findAccountByOwnerCin(ownerCin);
        double newBalance =  account.getBalance() + amount;
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

}
