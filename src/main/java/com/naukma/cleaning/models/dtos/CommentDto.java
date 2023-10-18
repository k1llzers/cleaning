package com.naukma.cleaning.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {
    private long id;
    private String text;
    private LocalDateTime date;
    private int rate;
}
