package com.naukma.cleaning.services.discountService;

import com.naukma.cleaning.models.order.DiscountDto;
import com.naukma.cleaningstarter.Discount;

public interface DiscountService {
    void createDiscount(DiscountDto discount);

    void editDiscount(DiscountDto discount);

    void deleteDiscount(long id);

    DiscountDto getDiscount(long id);

    DiscountDto getCurrentDiscount();
}
