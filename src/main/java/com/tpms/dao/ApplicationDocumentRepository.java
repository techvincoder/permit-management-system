package com.tpms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpms.entities.ApplicationDocument;

public interface ApplicationDocumentRepository extends JpaRepository<ApplicationDocument, Long>{

}
