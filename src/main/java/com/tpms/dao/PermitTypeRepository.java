package com.tpms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tpms.entities.Council;
import com.tpms.entities.PermitType;

public interface PermitTypeRepository extends JpaRepository<PermitType, Long>{

	@Query("SELECT pt.council FROM PermitType pt WHERE pt.id = :permitTypeId")
	Council getCouncilByPermitTypeId(@Param("permitTypeId") Long permitTypeId);

}
