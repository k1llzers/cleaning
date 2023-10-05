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
    @Column(nullable = false)
    private double price;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime orderTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime creationTime;
    @OneToOne
    @JoinColumn(name = "client_fk", nullable = false)
    private UserEntity client;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_executors",
            joinColumns = @JoinColumn(name = "order_fk"),
            inverseJoinColumns = @JoinColumn(name = "executor_fk"))
    private Set<UserEntity> executors;
    @OneToOne
    private CommentEntity comment;
    @OneToOne
    @JoinColumn(name = "address_fk", nullable = false)
    private AddressEntity address;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status orderStatus;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_proposals",
            joinColumns = @JoinColumn(name = "order_fk"),
            inverseJoinColumns = @JoinColumn(name = "proposal_fk"))
    private Set<CommercialProposalEntity> commercialProposals;
}
