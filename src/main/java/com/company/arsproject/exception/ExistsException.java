package com.company.arsproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ExistsException extends RuntimeException {
    public ExistsException(String message) {
        super(message);
    }
}
