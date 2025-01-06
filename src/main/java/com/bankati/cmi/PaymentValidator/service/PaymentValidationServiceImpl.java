package com.bankati.cmi.PaymentValidator.service;

import com.bankati.cmi.PaymentValidator.enums.PaymentType;
import com.bankati.cmi.PaymentValidator.validation.PaymentValidationStrategy;
import com.bankati.cmi.PaymentValidator.validation.strategy.*;
import com.bankati.cmi.payment.PaymentRequest;
import com.bankati.cmi.payment.PaymentResponse;
import com.bankati.cmi.payment.ValidatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentValidationServiceImpl implements PaymentValidationService {

    private final Map<PaymentType, PaymentValidationStrategy> validationStrategies = new EnumMap<>(PaymentType.class);
    private TransferValidationStrategy transferValidationStrategy;
    private BillPaymentValidationStrategy billPaymentValidationStrategy;
    private  MerchantPaymentValidationStrategy merchantPaymentValidationStrategy;
    private MobileRechargeValidationStrategy mobileRechargeValidationStrategy;
    private WithdrawalValidationStrategy withdrawalValidationStrategy;
    private DepositValidationStrategy depositValidationStrategy;
    private RefundValidationStrategy refundValidationStrategy;
    @Autowired
    public PaymentValidationServiceImpl(TransferValidationStrategy transferValidationStrategy,
                                        BillPaymentValidationStrategy billPaymentValidationStrategy,
                                        MerchantPaymentValidationStrategy merchantPaymentValidationStrategy,
                                        MobileRechargeValidationStrategy mobileRechargeValidationStrategy,
                                        WithdrawalValidationStrategy withdrawalValidationStrategy,
                                        DepositValidationStrategy depositValidationStrategy,
                                        RefundValidationStrategy refundValidationStrategy
    ) {
        validationStrategies.put(PaymentType.TRANSFER, transferValidationStrategy);
        validationStrategies.put(PaymentType.BILL_PAYMENT, billPaymentValidationStrategy);
        validationStrategies.put(PaymentType.MERCHANT_PAYMENT, merchantPaymentValidationStrategy);
        validationStrategies.put(PaymentType.MOBILE_RECHARGE, mobileRechargeValidationStrategy);
        validationStrategies.put(PaymentType.WITHDRAWAL, withdrawalValidationStrategy);
        validationStrategies.put(PaymentType.DEPOSIT, depositValidationStrategy);
        validationStrategies.put(PaymentType.REFUND, refundValidationStrategy);
    }
    @Override
    public ValidatePaymentResponse validatePayment(PaymentRequest paymentRequest) {
        PaymentType paymentType;
        try {
            if (paymentRequest.getPaymentType() == null) {
                throw new IllegalArgumentException("Le type de paiement ne doit pas être null");
            }
            paymentType = PaymentType.fromCode(paymentRequest.getPaymentType());
        }catch (IllegalArgumentException e){
            PaymentResponse paymentResponse = new PaymentResponse();
            ValidatePaymentResponse validatePaymentResponse = new ValidatePaymentResponse();
            paymentResponse.setStatus("FAILED");
            paymentResponse.setMessage(e.getMessage());
            validatePaymentResponse.setPaymentResponse(paymentResponse);
            return  validatePaymentResponse;
        }

        PaymentValidationStrategy strategy = validationStrategies.get(paymentType);
        if (strategy == null) {
            PaymentResponse paymentResponse = new PaymentResponse();
            ValidatePaymentResponse validatePaymentResponse = new ValidatePaymentResponse();
            paymentResponse.setStatus("FAILED");
            paymentResponse.setMessage("Stratégie de validation inconnue pour le type de paiement: " + paymentRequest.getPaymentType());
            validatePaymentResponse.setPaymentResponse(paymentResponse);
            return  validatePaymentResponse;
        }
        try {
            PaymentResponse paymentResponse = new PaymentResponse();
            ValidatePaymentResponse validatePaymentResponse = new ValidatePaymentResponse();
            strategy.validatePayment(paymentRequest);
            paymentResponse.setStatus("SUCCESS");
            paymentResponse.setMessage("payment validated");
            validatePaymentResponse.setPaymentResponse(paymentResponse);
            return validatePaymentResponse;
        }catch (IllegalArgumentException e){
            PaymentResponse paymentResponse = new PaymentResponse();
            ValidatePaymentResponse validatePaymentResponse = new ValidatePaymentResponse();
            paymentResponse.setStatus("FAILED");
            paymentResponse.setMessage(e.getMessage());
            validatePaymentResponse.setPaymentResponse(paymentResponse);
            return validatePaymentResponse;
        }
    }
}