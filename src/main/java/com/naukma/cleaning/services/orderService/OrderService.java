package com.naukma.cleaning.services.orderService;

import com.naukma.cleaning.models.order.Order;
import com.naukma.cleaning.models.order.Status;

public interface OrderService {
    void createOrder(Order order);
    void editOrder(Order order);
    void changeStatus(Order order, Status status);
    Order getOrder(long id);
}
