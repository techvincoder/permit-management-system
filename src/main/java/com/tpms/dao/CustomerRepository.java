package com.tpms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tpms.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	@Query("select c from Customer c where c.email= :email")
	public Customer getCustomerByUsername(@Param("email") String email);
	
	Optional<Customer> findByEmail(String email);
}
