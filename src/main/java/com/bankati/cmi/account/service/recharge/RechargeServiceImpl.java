package com.bankati.cmi.account.service.recharge;

import com.bankati.cmi.account.AccountRechargeRequest;
import com.bankati.cmi.account.AccountRechargeResponse;
import com.bankati.cmi.account.RechargeRequest;
import com.bankati.cmi.account.RechargeResponse;
import com.bankati.cmi.account.model.Account;
import com.bankati.cmi.account.model.RechargeTransaction;
import com.bankati.cmi.account.repository.AccountRepository;
import com.bankati.cmi.account.repository.RechargeRepository;
import com.bankati.cmi.enums.RechargeType;
import com.bankati.cmi.utils.DateUtils;
import com.bankati.cmi.utils.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RechargeServiceImpl implements RechargeService {

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
}