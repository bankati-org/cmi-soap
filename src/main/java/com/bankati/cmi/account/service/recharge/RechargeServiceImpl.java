package com.bankati.cmi.account.service.recharge;

import com.bankati.cmi.account.*;
import com.bankati.cmi.account.model.Account;
import com.bankati.cmi.account.model.RechargeTransaction;
import com.bankati.cmi.account.repository.AccountRepository;
import com.bankati.cmi.account.repository.RechargeRepository;
import com.bankati.cmi.adapter.WalletAdapter;
import com.bankati.cmi.dto.depositRequest;
import com.bankati.cmi.enums.RechargeType;
import com.bankati.cmi.utils.DateUtils;
import com.bankati.cmi.utils.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RechargeServiceImpl implements RechargeService {
    private final WalletAdapter walletAdapter;
    private static final Logger log = LoggerFactory.getLogger(RechargeServiceImpl.class);
    private final AccountRepository accountRepository;
    private final RechargeRepository rechargeRepository;

    @Transactional
    public AccountRechargeResponse processRecharge(AccountRechargeRequest accountRechargeRequest) {
        try {
            RechargeRequest rechargeRequest = accountRechargeRequest.getRechargeRequest();
            log.info("Start processing recharge for CIN: {}", rechargeRequest.getOwnerCin());

            Account account = accountRepository.findAccountByOwnerCin(rechargeRequest.getOwnerCin());

            ValidationUtils.validateAccount(account, rechargeRequest.getOwnerCin());
            ValidationUtils.validateRechargeType(rechargeRequest.getRechargeType());
            ValidationUtils.validateAmount(rechargeRequest.getAmount());

            account.setBalance(account.getBalance() + rechargeRequest.getAmount());
            accountRepository.save(account);

            RechargeTransaction rechargeTransaction = new RechargeTransaction();
            rechargeTransaction.setRechargeType(RechargeType.valueOf(rechargeRequest.getRechargeType()));
            rechargeTransaction.setAccountNumber(account.getAccountNumber());
            rechargeTransaction.setStatus("SUCCESS");
            rechargeTransaction.setTransactionId(UUID.randomUUID().toString());
            rechargeTransaction.setUserId(rechargeRequest.getUserId());
            rechargeTransaction.setAmount(rechargeRequest.getAmount());
            rechargeTransaction.setTransactionDate(LocalDateTime.now());

            RechargeTransaction savedOne = rechargeRepository.save(rechargeTransaction);

            RechargeResponse rechargeResponse = new RechargeResponse();
            rechargeResponse.setStatus(savedOne.getStatus());
            rechargeResponse.setMessage("Recharge successful");
            rechargeResponse.setTransactionId(savedOne.getTransactionId());
            rechargeResponse.setNewBalance(account.getBalance());
            rechargeResponse.setTransactionDate(DateUtils.toXMLGregorianCalendar(savedOne.getTransactionDate()));

            AccountRechargeResponse response = new AccountRechargeResponse();
            response.setRechargeResponse(rechargeResponse);
            return response;

        } catch (Exception e) {
            log.error("Error during recharge process", e);
            throw e;
        }
    }

    @Override
    public WalletDepositResponse depositToWallet(WalletDepositRequest walletDepositRequest) {
        log.info("Start deposit to wallet for user: {}", walletDepositRequest.getDepositRequest().getOwnerCin());
        ValidationUtils.validateDepositRequest(walletDepositRequest.getDepositRequest());
        Account account = accountRepository.findAccountByOwnerCin(walletDepositRequest.getDepositRequest().getOwnerCin());
        if (account != null) {
            walletAdapter.depositToWallet(new depositRequest(account.getId(), walletDepositRequest.getDepositRequest().getBalance()));
        } else {
            try {
                throw new AccountNotFoundException("Account not found for CIN: " + walletDepositRequest.getDepositRequest().getOwnerCin());
            } catch (AccountNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return new WalletDepositResponse();
    }
}