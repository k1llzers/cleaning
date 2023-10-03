package com.naukma.cleaning.dao;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CommentEntity {
    @Id
    @GeneratedValue
    private long id;
    private int rate;
    @Column(length = 1000)
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
}
