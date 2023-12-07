package com.naukma.cleaning.batch;

import com.naukma.cleaning.dao.entities.OrderEntity;
import com.naukma.cleaning.dao.entities.ReportEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class StatisticsProcessor implements ItemProcessor<List<OrderEntity>, ReportEntity> {
    @Override
    public ReportEntity process(List<OrderEntity> list) throws Exception {
        if (list == null) {
            log.error("LIST NULL");
        }
        log.info("BATCH: start processing orders data");
        BigDecimal totalMoney = new BigDecimal("0");
        long orderCounter = 0;
        for (OrderEntity entity : list) {
            totalMoney = totalMoney.add(BigDecimal.valueOf(entity.getPrice()));
            orderCounter++;
        }
        ReportEntity report = new ReportEntity();
        report.setTotalPrice(totalMoney.doubleValue());
        report.setCountOfOrders(orderCounter);
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
        report.setPeriod(startDate + " " + endDate);
        return report;
    }
}
