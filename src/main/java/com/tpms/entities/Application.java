package com.tpms.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="application")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="application_id")
	private Long id;
	
	@Column(name="application_no")
	private String applicationNo;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private String details;
	
	@Column(name="estimated_cost")
	private BigDecimal estimatedCost;
	
	@Column(name = "submitted_at")
	private LocalDateTime submittedAt;
	
	@ManyToOne
	@JoinColumn(name="council_id")
	private Council council;
	
	@ManyToOne
	@JoinColumn(name="property_id")
	private Property property;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="permit_type_id")
	private PermitType permitType;
	
	@ManyToOne
	@JoinColumn(name="assigned_staff_account_id")
	private StaffAccounts assignedStaff;
	
	@OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ApplicationDocument> applicationDocuments = new HashSet<>();
	
	@OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ApplicationHistory> applicationHistory = new HashSet<>();
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name ="updated_at")
	private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}

	public Council getCouncil() {
		return council;
	}

	public void setCouncil(Council council) {
		this.council = council;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PermitType getPermitType() {
		return permitType;
	}

	public void setPermitType(PermitType permitType) {
		this.permitType = permitType;
	}

	public StaffAccounts getStaffAccount() {
		return assignedStaff;
	}

	public void setStaffAccount(StaffAccounts staffAccount) {
		this.assignedStaff = staffAccount;
	}

	public Set<ApplicationDocument> getApplicationDocuments() {
		return applicationDocuments;
	}

	public void setApplicationDocuments(Set<ApplicationDocument> applicationDocuments) {
		this.applicationDocuments = applicationDocuments;
	}

	public Set<ApplicationHistory> getApplicationHistory() {
		return applicationHistory;
	}

	public void setApplicationHistory(Set<ApplicationHistory> applicationHistory) {
		this.applicationHistory = applicationHistory;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "ApplicationRepository [id=" + id + ", applicationNo=" + applicationNo + ", status=" + status + ", details="
				+ details + ", estimatedCost=" + estimatedCost + ", submittedAt=" + submittedAt + ", council=" + council
				+ ", property=" + property + ", customer=" + customer + ", permitType=" + permitType + ", staffAccount="
				+ assignedStaff + ", applicationDocuments=" + applicationDocuments + ", applicationHistory="
				+ applicationHistory + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	public Application(long id, String applicationNo, Status status, String details, BigDecimal estimatedCost,
			LocalDateTime submittedAt, Council council, Property property, Customer customer, PermitType permitType,
			StaffAccounts staffAccount, Set<ApplicationDocument> applicationDocuments,
			Set<ApplicationHistory> applicationHistory, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.applicationNo = applicationNo;
		this.status = status;
		this.details = details;
		this.estimatedCost = estimatedCost;
		this.submittedAt = submittedAt;
		this.council = council;
		this.property = property;
		this.customer = customer;
		this.permitType = permitType;
		this.assignedStaff = staffAccount;
		this.applicationDocuments = applicationDocuments;
		this.applicationHistory = applicationHistory;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	
}
