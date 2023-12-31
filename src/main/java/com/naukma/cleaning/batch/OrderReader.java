package com.naukma.cleaning.batch;

import com.naukma.cleaning.dao.OrderDao;
import com.naukma.cleaning.dao.entities.OrderEntity;
import com.naukma.cleaning.models.order.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class OrderReader implements ItemReader<List<OrderEntity>> {
    @Autowired
    private OrderDao orderDao;
    private boolean done;
    @Override
    public List<OrderEntity> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (done) {
            return List.of(null);
        }
        log.info("Starting BATCH");
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
        List<OrderEntity> orders2 = orderDao.findAll();
        List<OrderEntity> orders =
                orderDao.findAllByOrderTimeLessThanEqualAndOrderTimeGreaterThanEqualAndOrderStatusIs(endDate.atStartOfDay(), startDate.atStartOfDay(), Status.DONE);
        if(orders == null || orders.isEmpty()) {
            log.warn("No orders found for the given period and status");
            return List.of(null);
        }
        log.info("Retrieved {} orders", orders.size());
        done = true;
        return orders;
    }
}
