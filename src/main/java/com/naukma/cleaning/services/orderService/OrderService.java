package com.naukma.cleaning.services.orderService;

import com.naukma.cleaning.models.order.OrderDto;
import com.naukma.cleaning.models.order.Status;

public interface OrderService {
    void createOrder(OrderDto orderDto);
    void editOrder(OrderDto orderDto);
    void changeStatus(OrderDto orderDto, Status status);
    OrderDto getOrder(long id);
}
