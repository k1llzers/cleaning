package com.naukma.cleaning.models.order;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Comment {
    private long id;
    @NonNull
    private String text;
    @NonNull
    private int rate;
}
