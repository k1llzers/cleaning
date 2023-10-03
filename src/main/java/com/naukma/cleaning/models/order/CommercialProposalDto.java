package com.naukma.cleaning.models.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommercialProposalDto {
    private String name;
    private String description;
    private double price;

}
