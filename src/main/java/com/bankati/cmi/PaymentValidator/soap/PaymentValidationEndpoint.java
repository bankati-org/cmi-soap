package com.bankati.cmi.PaymentValidator.soap;

import com.bankati.cmi.PaymentValidator.exception.ValidationException;
import com.bankati.cmi.PaymentValidator.service.PaymentValidationService;
import com.bankati.cmi.payment.PaymentResponse;
import com.bankati.cmi.payment.ValidatePaymentRequest;
import com.bankati.cmi.payment.ValidatePaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PaymentValidationEndpoint {
    private static final String NAMESPACE_URI = "http://bankati.com/cmi/payment";
    private final PaymentValidationService validationService;

    @Autowired
    public PaymentValidationEndpoint(PaymentValidationService validationService) {
        this.validationService = validationService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "validatePaymentRequest")
    @ResponsePayload
    public ValidatePaymentResponse validatePaymentRequest(@RequestPayload ValidatePaymentRequest validatePaymentRequest)
            throws ValidationException {
        try {
            return  validationService.validatePayment(validatePaymentRequest.getPaymentRequest());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid payment request: " + e.getMessage());
        } catch (Exception e) {
            throw new ValidationException("Unexpected error: " + e.getMessage());
        }
    }
}