package com.bankati.cmi.utils;

import com.bankati.cmi.account.DepositRequest;
import com.bankati.cmi.exceptions.ValidationException;

public class ValidationUtils {

    public static void validateAccount(Object account, String ownerCin) {
        if (account == null) {
            throw new ValidationException("Account not found for CIN: " + ownerCin);
        }
    }

    public static void validateRechargeType(String rechargeType) {
        if (rechargeType == null || rechargeType.isBlank()) {
            throw new ValidationException("Recharge type cannot be null or blank");
        }
    }

    public static void validateAmount(double amount) {
        if (amount <= 0) {
            throw new ValidationException("Amount must be greater than zero");
        }
    }

    public static void validateDepositRequest(DepositRequest depositRequest) {
        if (depositRequest == null) {
            throw new ValidationException("Deposit request cannot be null");
        }

        if (depositRequest.getOwnerCin() == null) {
            throw new ValidationException("User ID cannot be null");
        }

        if (depositRequest.getBalance() == 0) {
            throw new ValidationException("Balance cannot be null");
        }
    }
}
