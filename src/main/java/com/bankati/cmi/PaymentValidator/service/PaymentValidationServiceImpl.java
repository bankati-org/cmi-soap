package com.bankati.cmi.PaymentValidator.service;

import com.bankati.cmi.Account.model.Account;
import com.bankati.cmi.Account.service.AccountService;
import com.bankati.cmi.payment.PaymentRequest;
import com.bankati.cmi.payment.PaymentResponse;
import com.bankati.cmi.payment.ValidatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentValidationServiceImpl implements PaymentValidationService {

    private final AccountService accountService;

    @Override
    public ValidatePaymentResponse validatePayment(PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse();
        ValidatePaymentResponse validatePaymentResponse = new ValidatePaymentResponse();
        // Vérifier l'existence du compte source
        Account account = accountService.getAccountDetails(paymentRequest.getSourceAccount());
        if (account == null) {
            paymentResponse.setMessage("Compte source introuvable");
            paymentResponse.setStatus("FAILED");
            validatePaymentResponse.setPaymentResponse(paymentResponse);
        }

        Account destinationAccount = accountService.getAccountDetails(paymentRequest.getTargetAccount());
        if (destinationAccount == null) {
            paymentResponse.setMessage("Compte destinataire introuvable");
            paymentResponse.setStatus("FAILED");
            validatePaymentResponse.setPaymentResponse(paymentResponse);
        }

        // Vérifier que le solde est suffisant
        assert account != null;
        if (account.getBalance() < paymentRequest.getAmount() || paymentRequest.getAmount() <= 0) {
            paymentResponse.setMessage("Solde insuffisant pour effectuer le paiement");
            paymentResponse.setStatus("FAILED");
            validatePaymentResponse.setPaymentResponse(paymentResponse);
        }
        // Validation réussie
        return validatePaymentResponse;
    }
}
