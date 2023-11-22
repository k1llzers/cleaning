package com.naukma.cleaning.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ExceptionHandlerAspect {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerAspect.class);
    @Pointcut("execution(* com.naukma.cleaning.controllers.CommercialProposalController.*(..))")
    void cut() {}

    @AfterThrowing(value = "cut()", throwing = "exception")
    void handleException(JoinPoint jp, Throwable exception) {
        logger.warn("{} occurred in method {}, with args {}",exception.getClass().getName(), jp.getSignature().getName()
                , Arrays.toString(jp.getArgs()));
    }
}
