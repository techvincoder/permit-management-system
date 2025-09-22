package com.tpms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}
