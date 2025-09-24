package com.tpms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.Council;

public interface CouncilRepository extends JpaRepository<Council, Long>{

	
	Optional<Council> findByName(String name);
}
