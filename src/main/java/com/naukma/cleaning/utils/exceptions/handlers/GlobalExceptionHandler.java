package com.naukma.cleaning.utils.exceptions.handlers;

import com.naukma.cleaning.utils.exceptions.PaymentUnsuccessfulException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PaymentUnsuccessfulException.class)
    public String handlePaymentException(HttpServletRequest request, PaymentUnsuccessfulException e) {
        logger.error("Payment exception occurred. URL={}, Message={}", request.getRequestURL(), e.getMessage());
        return "payment_error";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(WebRequest request, NoSuchElementException e) {
        logger.error("Can`t find entity by {}", ((ServletWebRequest) request).getRequest().getRequestURL());
        return handleExceptionInternal(e,"can`t find entity",new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }
}
