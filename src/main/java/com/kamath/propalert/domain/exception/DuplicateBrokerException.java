package com.kamath.propalert.domain.exception;

public class DuplicateBrokerException extends RuntimeException{
    public DuplicateBrokerException(String message) {
        super(message);
    }
}
