package com.tpms.entities;

import java.time.LocalDateTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="application_document")
public class ApplicationDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_document_id")
	private Long id;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "storage_path")
	private String storagePath;
	
	@Column(name = "document_category")
	private String documentCategory;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name="application_id")
	//@JsonBackReference
	private Application application;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public String getDocumentCategory() {
		return documentCategory;
	}

	public void setDocumentCategory(String documentCategory) {
		this.documentCategory = documentCategory;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "ApplicationDocument [id=" + id + ", filePath=" + filePath + ", StoragePath=" + storagePath
				+ ", documentCategory=" + documentCategory + ", updatedAt=" + updatedAt + ", application=" + application
				+ "]";
	}

	public ApplicationDocument(Long id, String filePath, String storagePath, String documentCategory,
			LocalDateTime updatedAt, Application application) {
		super();
		this.id = id;
		this.filePath = filePath;
		this.storagePath = storagePath;
		this.documentCategory = documentCategory;
		this.updatedAt = updatedAt;
		this.application = application;
	}

	public ApplicationDocument() {
		super();
	}
	
	
	
	
}
