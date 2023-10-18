package com.naukma.cleaning.models.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommercialProposal {
    private long id;
    private String name;
    private String description;
    private double price;
}
