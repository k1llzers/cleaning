package com.naukma.cleaning.services.pricingService;

import com.naukma.cleaning.models.order.OrderDto;
import com.naukma.cleaning.models.order.CommercialProposalDto;
import com.naukma.cleaningstarter.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceImpl implements PricingService {
    private DiscountService discountService;
    @Value("${fee:20}")
    private int fee;

    @Autowired
    public PricingServiceImpl(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public double calculate(OrderDto orderDto) {
        return orderDto.getCommercialProposalDtos().stream().mapToDouble(CommercialProposalDto::getPrice).sum()
                * discountService.getCurrentDiscount().getDiscountPercent() * (1 + fee / 100.0);
    }
}
