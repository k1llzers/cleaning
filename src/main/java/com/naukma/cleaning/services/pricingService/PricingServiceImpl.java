package com.naukma.cleaning.services.pricingService;

import com.naukma.cleaning.models.order.Order;
import com.naukma.cleaning.models.order.Proposal;
import com.naukma.cleaning.services.discountService.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceImpl implements PricingService {
    private DiscountService discountService;

    @Autowired
    public PricingServiceImpl(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public double calculate(Order order) {
        return order.getProposals().stream().mapToDouble(Proposal::getPrice).sum()
                * discountService.getCurrentDiscount().getDiscountPercent();
    }
}
