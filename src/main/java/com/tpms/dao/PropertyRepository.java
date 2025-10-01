package com.tpms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

	
	List<Property> findByCustomerEmail(String email);
	
}
