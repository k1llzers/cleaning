package com.naukma.cleaning.models.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentDto {
    private long id;
    private String text;
    private LocalDateTime date;
    private int rate;
}
