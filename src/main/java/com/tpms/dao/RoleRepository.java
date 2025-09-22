package com.tpms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
