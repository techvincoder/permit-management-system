package com.tpms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tpms.entities.StaffAccounts;

public interface StaffAccountsRepository extends JpaRepository<StaffAccounts, Long> {

	 Optional<StaffAccounts> findByEmail(String email);
	 
	 @Query("select s from StaffAccounts s where s.email = :email")
	 public StaffAccounts getStaffByUsername(@Param("email") String email);
}
