package com.naukma.cleaning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naukma.cleaning.dao.entities.CommercialProposalEntity;

public interface CommercialProposalDao extends JpaRepository<CommercialProposalEntity, Long> {
    
}
