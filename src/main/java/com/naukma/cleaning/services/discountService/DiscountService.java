package com.naukma.cleaning.services.discountService;

import com.naukma.cleaning.models.order.Discount;

public interface DiscountService {
    void createDiscount(Discount discount);

    void editDiscount(Discount discount);

    void deleteDiscount(long id);

    Discount getDiscount(long id);

    Discount getCurrentDiscount();
}
