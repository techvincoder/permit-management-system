package com.tpms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{
	
	List<Application> findByCustomerEmail(String email);

}
