package com.naukma.cleaning.dao.entities;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
