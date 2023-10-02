package com.naukma.cleaning.dao;

import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.order.Comment;
import com.naukma.cleaning.models.order.CommercialProposal;
import com.naukma.cleaning.models.order.Status;
import com.naukma.cleaning.models.user.User;
import jakarta.persistence.*;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue
    private long id;
    private double price;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderTime;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationTime;
    @OneToOne
    private UserEntity client;
    @ManyToMany
    @JoinTable(name = "order_executors",
            joinColumns = @JoinColumn(name = "order_fk"),
            inverseJoinColumns = @JoinColumn(name = "executor_fk"))
    private Set<UserEntity> executors;
    @OneToOne
    private CommentEntity comment;
    @OneToOne
    private AddressEntity address;
    @Enumerated(EnumType.STRING)
    private Status orderStatus;
    @ManyToMany
    @JoinTable(name = "order_proposals",
            joinColumns = @JoinColumn(name = "order_fk"),
            inverseJoinColumns = @JoinColumn(name = "proposal_fk"))
    private Set<CommercialProposalEntity> commercialProposals;
}
