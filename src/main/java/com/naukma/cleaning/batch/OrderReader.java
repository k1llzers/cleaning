package com.naukma.cleaning.batch;

import com.naukma.cleaning.dao.OrderDao;
import com.naukma.cleaning.dao.entities.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OrderReader implements ItemReader<List<OrderEntity>> {
    @Autowired
    private OrderDao orderDao;
    @Override
    public List<OrderEntity> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.info("Reading orders");
        // TODO FIND IN PERIOD
        List<OrderEntity> orders = orderDao.findAll();
        if(orders == null || orders.size()==0){
            return null;
        }
        return orders;
    }
}
