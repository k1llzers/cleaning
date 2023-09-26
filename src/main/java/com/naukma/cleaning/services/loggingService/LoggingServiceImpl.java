package com.naukma.cleaning.services.loggingService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnResource(resources = "logback.txt")
public class LoggingServiceImpl implements LoggingService {
    @Override
    public void log(String str) {

    }
}
