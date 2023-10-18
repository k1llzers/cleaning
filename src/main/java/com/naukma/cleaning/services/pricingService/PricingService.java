package com.naukma.cleaning.services.pricingService;

import com.naukma.cleaning.models.order.Order;

public interface PricingService {
    double calculate(Order order);
}