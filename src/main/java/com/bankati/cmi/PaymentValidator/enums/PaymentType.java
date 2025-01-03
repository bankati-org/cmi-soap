package com.bankati.cmi.PaymentValidator.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum PaymentType {
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

    PaymentType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    public static PaymentType fromCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Le code du payment type ne peut pas être null ou vide.");
        }
        for (PaymentType paymentType : values()) {
            if (paymentType.getCode().equalsIgnoreCase(code)) {
                return paymentType;
            }
        }
        throw new IllegalArgumentException("Aucun payment type trouvé avec le code: " + code);
    }
}