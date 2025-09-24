package com.tpms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.StaffAccounts;

public interface StaffAccountsRepository extends JpaRepository<StaffAccounts, Long> {

	 Optional<StaffAccounts> findByEmail(String email);
}
