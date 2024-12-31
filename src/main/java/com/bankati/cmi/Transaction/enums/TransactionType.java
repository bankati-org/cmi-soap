package com.bankati.cmi.Transaction.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TransactionType {
    TRANSFER("Transfer", "Transfert d'argent entre utilisateurs"),
    BILL_PAYMENT("Bill", "Paiement de factures"),
    MERCHANT_PAYMENT("Merchant", "Paiement marchand"),
    RECURRING_PAYMENT("Recurring", "Paiement récurrent"),
    MOBILE_RECHARGE("Recharge", "Recharge mobile"),
    WITHDRAWAL("Withdrawal", "Retrait d'argent"),
    DEPOSIT("Deposit", "Dépôt d'argent"),
    REFUND("Refund", "Remboursement");

    private final String code;
    private final String description;

    TransactionType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static TransactionType fromCode(String code) {
        return Arrays.stream(TransactionType.values())
                .filter(type -> type.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid TransactionType type code: " + code));
    }
}
