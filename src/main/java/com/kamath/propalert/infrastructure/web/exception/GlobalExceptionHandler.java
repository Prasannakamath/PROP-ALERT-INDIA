package com.kamath.propalert.infrastructure.web.exception;

import com.kamath.propalert.domain.exception.DuplicateLeadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kamath.propalert.domain.exception.DuplicateBrokerException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateBrokerException.class)
    public ProblemDetail handleDuplicateBrokerException(DuplicateBrokerException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,ex.getMessage());
        problemDetail.setTitle("Duplicate Broker");
        return problemDetail;
    }

    @ExceptionHandler(DuplicateLeadException.class)
    public ProblemDetail handleDuplicateLeadException(DuplicateLeadException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,ex.getMessage());
        problemDetail.setTitle("Duplicate Lead");
        return problemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleValidationException(IllegalArgumentException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
        problemDetail.setTitle("Bad Request");
        return problemDetail;
    }
}
