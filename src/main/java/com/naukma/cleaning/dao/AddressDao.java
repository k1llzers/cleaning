package com.naukma.cleaning.dao;

import com.naukma.cleaning.dao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.naukma.cleaning.dao.entities.AddressEntity;

import java.util.List;

public interface AddressDao extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findAddressEntitiesByUserEntity(UserEntity userEntity);
}
