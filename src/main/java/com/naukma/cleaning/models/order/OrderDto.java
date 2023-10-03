package com.naukma.cleaning.models.order;

import com.naukma.cleaning.models.user.UserDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
@RequiredArgsConstructor
public class OrderDto {
    private long id;
    @NonNull
    private double price;
    @NonNull
    private LocalDateTime orderTime;
    @NonNull
    private LocalDateTime creationTime;
    @NonNull
    private UserDto client;
    @NonNull
    private Set<UserDto> executors;
    @NonNull
    private CommentDto commentDto;
    @NonNull
    private AddressDto addressDto;
    private Status orderStatus = Status.NOT_STARTED;
    @NonNull
    private Set<CommercialProposalDto> commercialProposalDtos;
}
