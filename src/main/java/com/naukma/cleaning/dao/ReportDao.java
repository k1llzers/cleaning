package com.naukma.cleaning.dao;

import com.naukma.cleaning.dao.entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportDao extends JpaRepository<ReportEntity, Long> {
}
