package com.naukma.cleaning.models.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiscountDto {
    private String name;
    private double discountPercent;
}
