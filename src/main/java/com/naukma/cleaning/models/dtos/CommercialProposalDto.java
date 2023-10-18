package com.naukma.cleaning.models.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class CommercialProposalDto {
    private long id;
    private String name;
    private String description;
    private double price;
}
