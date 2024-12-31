package com.bankati.cmi.PaymentValidator.service;

import com.bankati.cmi.payment.PaymentRequest;
import com.bankati.cmi.payment.ValidatePaymentResponse;

public interface PaymentValidationService {
    ValidatePaymentResponse validatePayment(PaymentRequest paymentRequest);
}
