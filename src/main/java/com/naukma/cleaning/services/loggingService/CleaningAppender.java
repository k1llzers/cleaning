package com.naukma.cleaning.services.loggingService;

import ch.qos.logback.core.FileAppender;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CleaningAppender<E> extends FileAppender<E> {
    public CleaningAppender() {
        super();
        super.setFile("logs/myapp" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm")) + ".log");
    }
}
