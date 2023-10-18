package com.naukma.cleaning.utils.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PaymentUnsuccessfulException.class)
    public String handlePaymentException(HttpServletRequest request, PaymentUnsuccessfulException e) {
        logger.error("Payment exception occurred. URL={}, Message={}", request.getRequestURL(), e.getMessage());
        return "payment_error";
    }
}
