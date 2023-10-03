package com.naukma.cleaning.dao;

import com.naukma.cleaning.dao.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<CommentEntity, Long> {
    
}
