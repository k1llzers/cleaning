package com.naukma.cleaning.batch;

import com.naukma.cleaning.dao.ReportDao;
import com.naukma.cleaning.dao.entities.OrderEntity;
import com.naukma.cleaning.dao.entities.ReportEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// TODO REPLACE WITH STATISTICS ENTITY AND REPO
@Slf4j
@Component
public class StatisticsWriter2 implements ItemWriter<List<OrderEntity>> {
    @Autowired
    private ReportDao reportDao;

    @Override
    public void write(Chunk<? extends List<OrderEntity>> chunk) throws Exception {
//        if (chunk == null || chunk.getItems() == null || chunk.getItems().size() == 0) {
//            log.error("SOMETHING WRONG WITH CHUNK");
//        }
//        log.info("BATCH: writing orders report for {}", chunk.getItems().get(0).getPeriod());
//        reportDao.save(chunk.getItems().get(0));
//        log.info("End of BATCH EXECUTION");
        log.error("END");
    }

    //    @Override
//    public void write(Chunk<? extends List<OrderEntity>> chunk) throws Exception {
//        log.info("Writing: {}", chunk.getItems().size());
//        List<OrderEntity> students = chunk.getItems().stream().flatMap(List::stream).collect(Collectors.toList());
//        System.out.println("Writing orders");
//        for (OrderEntity entity : students) {
//            System.out.println(entity);
//        }
////        orderDao.saveAll(chunk.getItems());
//
//        List<OrderEntity> orders = chunk.getItems().stream()
//                .flatMap(List::stream)
//                .distinct()
//                .toList();
//        ReportEntity report = new ReportEntity();
//        report.setPeriod(LocalDate.now().minusMonths(1).atStartOfDay().toString()
//                + LocalDate.now().atStartOfDay().toString());
//        report.setCountOfOrders((long)orders.size());
//        report.setTotalPrice(orders.stream().mapToDouble(OrderEntity::getPrice).sum());
//
//        reportDao.save(report);
//    }
}