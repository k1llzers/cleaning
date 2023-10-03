package com.naukma.cleaning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naukma.cleaning.dao.entities.AddressEntity;

public interface AddressDao extends JpaRepository<AddressEntity, Long> {
    
}
