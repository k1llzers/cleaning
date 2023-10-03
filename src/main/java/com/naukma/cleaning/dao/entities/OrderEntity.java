package com.naukma.cleaning.dao.entities;

import com.naukma.cleaning.models.order.Status;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "cleaning_order")
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
