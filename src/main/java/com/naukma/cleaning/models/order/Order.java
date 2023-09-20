package com.naukma.cleaning.models.order;

import com.naukma.cleaning.models.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
@RequiredArgsConstructor
public class Order {
    private long id;
    @NonNull
    private double price;
    @NonNull
    private LocalDateTime orderTime;
    @NonNull
    private LocalDateTime creationTime;
    @NonNull
    private User client;
    @NonNull
    private Set<User> executors;
    @NonNull
    private Comment comment;
    @NonNull
    private Address address;
    private Status orderStatus = Status.NOT_STARTED;
}
