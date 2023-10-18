package com.naukma.cleaning.models.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Discount {
    private String name;
    private double discountPercent;
}
