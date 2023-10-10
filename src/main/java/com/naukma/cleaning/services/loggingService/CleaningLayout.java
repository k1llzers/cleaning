package com.naukma.cleaning.services.loggingService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import org.slf4j.Marker;

public class CleaningLayout extends LayoutBase<ILoggingEvent> {

  public String doLayout(ILoggingEvent event) {
    StringBuffer sbuf = new StringBuffer(128);
    sbuf.append(new Date(event.getTimeStamp()));
    sbuf.append(" | ");
    sbuf.append(event.getLevel());
    sbuf.append(" [");
    sbuf.append(event.getThreadName());
    sbuf.append("] ");
    List<Marker> markers = event.getMarkerList();
    if (markers != null && !markers.isEmpty()) {
      String m = markers.stream().map(Marker::getName).collect(Collectors.joining(" "));
      sbuf.append(m);
      sbuf.append(' ');
    }
    sbuf.append(event.getLoggerName());
    sbuf.append(" - ");
    sbuf.append(event.getFormattedMessage());
    sbuf.append(CoreConstants.LINE_SEPARATOR);
    return sbuf.toString();
  }
}