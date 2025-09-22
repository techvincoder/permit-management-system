package com.tpms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
