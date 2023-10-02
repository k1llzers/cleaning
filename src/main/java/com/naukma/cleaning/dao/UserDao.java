package com.naukma.cleaning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByEmail(String email);
}
