package com.naukma.cleaning.models.order;

import com.naukma.cleaning.models.user.UserDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private long id;
    private double price;
    private LocalDateTime orderTime;
    private LocalDateTime creationTime;
    private UserDto client;
    private Set<UserDto> executors;
    private CommentDto comment;
    private AddressDto address;
    private Status orderStatus = Status.NOT_STARTED;
    private Set<CommercialProposalDto> commercialProposals;
}
