package com.naukma.cleaning.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommercialProposalDto {
    private long id;
    private String name;
    private String description;
    private double price;
}
