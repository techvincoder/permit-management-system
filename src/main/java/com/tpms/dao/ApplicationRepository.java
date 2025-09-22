package com.tpms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{

}
