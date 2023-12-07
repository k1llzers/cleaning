package com.naukma.cleaning.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.naukma.cleaning.models.order.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import com.naukma.cleaning.dao.entities.AddressEntity;
import com.naukma.cleaning.dao.entities.OrderEntity;

public interface OrderDao extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findOrderEntitiesByAddress(AddressEntity address);
    List<OrderEntity> findAllByOrderTimeLessThanEqualAndOrderTimeGreaterThanEqualAndOrderStatusIs(LocalDateTime end, LocalDateTime start, Status status);
}
