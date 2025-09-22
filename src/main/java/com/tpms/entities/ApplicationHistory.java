package com.tpms.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="application_history")
public class ApplicationHistory {

	@Id
	@Column(name = "application_history_id")
	private Long id;
	
	@Column(name = "entry_type")
	private String entryType;
	
	private String description;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name="application_id")
	private Application application;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "ApplicationHistory [id=" + id + ", entryType=" + entryType + ", description=" + description
				+ ", createdAt=" + createdAt + ", application=" + application + "]";
	}

	public ApplicationHistory(Long id, String entryType, String description, LocalDateTime createdAt,
			Application application) {
		super();
		this.id = id;
		this.entryType = entryType;
		this.description = description;
		this.createdAt = createdAt;
		this.application = application;
	}

	public ApplicationHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
