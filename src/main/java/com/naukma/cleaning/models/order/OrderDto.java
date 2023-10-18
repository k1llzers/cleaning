package com.naukma.cleaning.models.order;

import com.naukma.cleaning.models.user.UserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @Min(value = 0, message = "Price could not be less than 0")
    private double price;
    @Future
    private LocalDateTime orderTime;
    @PastOrPresent
    private LocalDateTime creationTime;
    @Valid
    @NotNull(message = "Client cannot be null")
    private UserDto client;
    @NotEmpty(message = "Executors cannot be empty")
    private Set<UserDto> executors;
    //@Valid
    @NotNull(message = "Comment cannot be null")
    private CommentDto comment;
    //@Valid
    @NotNull(message = "Address cannot be null")
    private AddressDto address;
    @NotNull(message = "Order status cannot be null")
    private Status orderStatus = Status.NOT_STARTED;
    @NotEmpty(message = "Commercial proposals cannot be empty")
    private Set<CommercialProposalDto> commercialProposals;
}
