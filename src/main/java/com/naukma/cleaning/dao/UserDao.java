package com.naukma.cleaning.dao;

import com.naukma.cleaning.dao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByEmail(String email);
}
