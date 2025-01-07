package com.bankati.cmi.utils;

import com.bankati.cmi.account.model.Account;
import com.bankati.cmi.enums.AccountStatus;
import com.bankati.cmi.exceptions.*;
import com.bankati.cmi.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ValidationUtils {
    private static final Logger logger = LoggerFactory.getLogger(ValidationUtils.class);
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

    public static void validatePayment(Account senderAccount, Account recipientAccount, Transaction transaction) {
        logger.info("Starting payment validation for transaction between accounts {} and {}",
                senderAccount.getAccountNumber(), recipientAccount.getAccountNumber());

        validateAccountsExistence(senderAccount, recipientAccount);
        validateAccountsStatus(senderAccount, recipientAccount);
        validateTransactionAmount(senderAccount, transaction.getAmount());
    }

    private static void validateAccountsExistence(Account sender, Account recipient) {
        if (sender == null) {
            logger.error("Sender account not found");
            throw new AccountNotFoundException1("Sender account not found");
        }

        if (recipient == null) {
            logger.error("Recipient account not found");
            throw new AccountNotFoundException1("Recipient account not found");
        }
    }

    private static void validateAccountsStatus(Account sender, Account recipient) {
        if (!String.valueOf(AccountStatus.ACTIVE).equals(sender.getStatus())) {
            logger.error("Sender account {} is not active. Current status: {}",
                    sender.getAccountNumber(), sender.getStatus());
            throw new InvalidAccountStatusException("Sender account is not active");
        }

        if (!String.valueOf(AccountStatus.ACTIVE).equals(recipient.getStatus())) {
            logger.error("Recipient account {} is not active. Current status: {}",
                    recipient.getAccountNumber(), recipient.getStatus());
            throw new InvalidAccountStatusException("Recipient account is not active");
        }
    }

    private static void validateTransactionAmount(Account sender, Double amount) {
        if (amount <= 0) {
            logger.error("Invalid transaction amount: {}", amount);
            throw new InvalidTransactionAmountException("Transaction amount must be greater than 0");
        }

        if (sender.getBalance() < amount) {
            logger.error("Insufficient funds in sender account {}. Balance: {}, Amount: {}",
                    sender.getAccountNumber(), sender.getBalance(), amount);
            throw new InsufficientFundsException("Insufficient funds in sender account");
        }

    }

}
