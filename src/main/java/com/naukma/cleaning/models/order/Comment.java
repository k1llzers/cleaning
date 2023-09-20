package com.naukma.cleaning.models.order;

import com.naukma.cleaning.models.user.User;
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
    @NonNull
    private User user;

}
