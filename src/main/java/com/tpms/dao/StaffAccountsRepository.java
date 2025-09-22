package com.tpms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.StaffAccounts;

public interface StaffAccountsRepository extends JpaRepository<StaffAccounts, Long> {

}
