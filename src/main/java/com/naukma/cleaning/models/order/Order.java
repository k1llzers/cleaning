package com.naukma.cleaning.models.order;

import com.naukma.cleaning.models.user.User;
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
public class Order {
    private long id;
    @Min(value = 0, message = "Price could not be less than 0")
    private double price;
    @Future
    private LocalDateTime orderTime;
    @PastOrPresent
    private LocalDateTime creationTime;
    @Valid
    @NotNull(message = "Client cannot be null")
    private User client;
    @NotEmpty(message = "Executors cannot be empty")
    private Set<User> executors;
    //@Valid
    //@NotNull(message = "Comment cannot be null")
    private Comment comment;
    //@Valid
    @NotNull(message = "Address cannot be null")
    private Address address;
    @NotNull(message = "Order status cannot be null")
    private Status orderStatus = Status.NOT_STARTED;
    @NotEmpty(message = "Commercial proposals cannot be empty")
    private Set<CommercialProposal> commercialProposals;
}
