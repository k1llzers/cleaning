package com.naukma.cleaning.models.dtos;

import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.order.Comment;
import com.naukma.cleaning.models.order.CommercialProposal;
import com.naukma.cleaning.models.order.Status;
import com.naukma.cleaning.models.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
