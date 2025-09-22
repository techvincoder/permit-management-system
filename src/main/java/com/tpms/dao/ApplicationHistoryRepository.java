package com.tpms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.ApplicationHistory;

public interface ApplicationHistoryRepository extends JpaRepository<ApplicationHistory, Long> {

}
