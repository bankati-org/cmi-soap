package com.bankati.cmi.PaymentValidator.exception;


import jakarta.xml.ws.WebFault;
import lombok.Getter;

@Getter
@WebFault(name = "ValidationFault")
public class ValidationException extends Exception {
    private final ValidationFault faultInfo;

    public ValidationException(String message) {
        super(message);
        this.faultInfo = new ValidationFault(message);
    }

    public ValidationException(String message, ValidationFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
        this.faultInfo = new ValidationFault(message);
    }

}