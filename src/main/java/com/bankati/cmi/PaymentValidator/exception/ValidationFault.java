package com.bankati.cmi.PaymentValidator.exception;

public class ValidationFault {
    private String errorMessage;

    public ValidationFault(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}