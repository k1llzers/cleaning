package com.naukma.cleaning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naukma.cleaning.dao.entities.DiscountEntity;

public interface DiscountDao extends JpaRepository<DiscountEntity, Long> {
    
}
