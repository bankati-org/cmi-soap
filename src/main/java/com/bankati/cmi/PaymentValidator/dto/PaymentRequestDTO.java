package com.bankati.cmi.PaymentValidator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private String sourceAccount;
    private String targetAccount;
    private String currency;
    private double amount;
}

