package com.naukma.cleaning.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommercialProposalDto {
    private long id;
    @NotBlank
    @NotNull(message = "Name cannot be null")
    private String name;
    private String description;
    @Positive
    private double price;
}
