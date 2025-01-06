package com.bankati.cmi.PaymentValidator.validation.strategy;

import com.bankati.cmi.PaymentValidator.validation.PaymentValidationStrategy;
import com.bankati.cmi.account.model.Account;
import com.bankati.cmi.account.service.AccountService;
import com.bankati.cmi.payment.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillPaymentValidationStrategy implements PaymentValidationStrategy {
    private static final Logger log = LoggerFactory.getLogger(BillPaymentValidationStrategy.class);
    private final AccountService accountService;
    @Override
    public void validatePayment(PaymentRequest paymentRequest) {
        Account sourceAccount = accountService.getAccountDetails(paymentRequest.getSourceAccount());
        Account targetAccount = accountService.getAccountDetails(paymentRequest.getTargetAccount());
        log.info("amount :{} ",paymentRequest.getAmount());
        if (sourceAccount == null) {
            throw new IllegalArgumentException("Le compte source est obligatoire pour le paiement d'une facture.");
        }
        if (paymentRequest.getAmount() <= 0 | paymentRequest.getAmount() > sourceAccount.getBalance()) {
            throw new IllegalArgumentException("Le montant du paiement doit être supérieur à 0.");
        }
        if(targetAccount == null){
            throw new IllegalArgumentException("le compte du payee est obligatoire");
        }
        if(paymentRequest.getCurrency()== null | paymentRequest.getCurrency().isEmpty()){
            throw new IllegalArgumentException("La devise est obligatoire");
        }
    }
}