package com.naukma.cleaning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naukma.cleaning.dao.entities.DiscountEntity;

import java.time.LocalDateTime;

public interface DiscountDao extends JpaRepository<DiscountEntity, Long> {
    DiscountEntity getByStartLessThanEqualAndFinishGreaterThanEqual(LocalDateTime localDateTime);
}
