package com.bankati.cmi.PaymentValidator.validation;

import com.bankati.cmi.payment.PaymentRequest;

public interface PaymentValidationStrategy {
    void validatePayment(PaymentRequest request);
}