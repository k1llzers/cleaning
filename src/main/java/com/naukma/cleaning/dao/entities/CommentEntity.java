package com.naukma.cleaning.dao.entities;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private int rate;
    @Column(length = 1000, nullable = false)
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime date;
}
