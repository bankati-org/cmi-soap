package com.bankati.cmi.PaymentValidator.validation.strategy;


import com.bankati.cmi.payment.PaymentRequest;
import com.bankati.cmi.PaymentValidator.validation.PaymentValidationStrategy;
import org.springframework.stereotype.Component;

@Component
public class TransferValidationStrategy implements PaymentValidationStrategy {
    @Override
    public void validatePayment(PaymentRequest request) {
        if (request.getSourceAccount() == null || request.getTargetAccount() == null) {
            throw new IllegalArgumentException("Les comptes source et cible sont obligatoires pour un transfert.");
        }

        if (request.getSourceAccount().equals(request.getTargetAccount())) {
            throw new IllegalArgumentException("Le compte source et cible ne peuvent pas être identique");
        }
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Le montant du transfert doit être supérieur à 0.");
        }
        if(request.getCurrency()== null || request.getCurrency().isEmpty()){
            throw new IllegalArgumentException("La devise est obligatoire");
        }
    }

}