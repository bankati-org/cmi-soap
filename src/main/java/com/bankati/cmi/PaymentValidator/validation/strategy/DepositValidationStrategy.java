package com.bankati.cmi.PaymentValidator.validation.strategy;

import com.bankati.cmi.payment.PaymentRequest;
import com.bankati.cmi.PaymentValidator.validation.PaymentValidationStrategy;
import org.springframework.stereotype.Component;

@Component
public class DepositValidationStrategy implements PaymentValidationStrategy {
    public void validatePayment(PaymentRequest request) {
        if (request.getTargetAccount() == null) {
            throw new IllegalArgumentException("Le compte cible est obligatoire pour un dépôt.");
        }
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être supérieur à 0.");
        }
        if(request.getCurrency()== null || request.getCurrency().isEmpty()){
            throw new IllegalArgumentException("La devise est obligatoire");
        }
        if (request.getSourceAccount() != null ) {
            throw new IllegalArgumentException("Le compte source est interdit pour un dépôt.");
        }
    }
}