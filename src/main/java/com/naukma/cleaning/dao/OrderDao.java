package com.naukma.cleaning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naukma.cleaning.dao.entities.OrderEntity;

public interface OrderDao extends JpaRepository<OrderEntity, Long> {
    
}
