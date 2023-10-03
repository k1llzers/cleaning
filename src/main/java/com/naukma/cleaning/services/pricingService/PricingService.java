package com.naukma.cleaning.services.pricingService;

import com.naukma.cleaning.models.order.OrderDto;

public interface PricingService {
    double calculate(OrderDto orderDto);
}