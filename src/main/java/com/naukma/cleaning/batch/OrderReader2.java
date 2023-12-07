package com.naukma.cleaning.batch;

import com.naukma.cleaning.dao.OrderDao;
import com.naukma.cleaning.dao.entities.OrderEntity;
import com.naukma.cleaning.models.order.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class OrderReader2 implements ItemReader<List<OrderEntity>> {
    @Autowired
    private OrderDao orderDao;
    @Override
    public List<OrderEntity> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.error("Into reader");
//        return List.of(null);
        return orderDao.findAll();
    }
}
