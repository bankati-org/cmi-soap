package com.bankati.cmi.PaymentValidator.validation.strategy;

import com.bankati.cmi.payment.PaymentRequest;
import com.bankati.cmi.PaymentValidator.validation.PaymentValidationStrategy;
import org.springframework.stereotype.Component;

@Component
public class MobileRechargeValidationStrategy implements PaymentValidationStrategy {
    @Override
    public void validatePayment(PaymentRequest request) {
        if (request.getSourceAccount() == null) {
            throw new IllegalArgumentException("Le compte source est obligatoire pour une recharge mobile.");
        }
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Le montant de la recharge doit être supérieur à 0.");
        }
        if(request.getTargetAccount()== null){
            throw new IllegalArgumentException("le numero de telephone est obligatoire");
        }
        if(request.getCurrency()== null || request.getCurrency().isEmpty()){
            throw new IllegalArgumentException("La devise est obligatoire");
        }
    }
}