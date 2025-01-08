package com.bankati.cmi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException1 extends RuntimeException {
    public AccountNotFoundException1(String message) {
        super(message);
    }
}
