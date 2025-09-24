package com.tpms.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Note: We are using other DTOs for nested objects
public class ApplicationDto {
    private Long id;
    private String applicationNo;
    private String status;
    private String details;
    private BigDecimal estimatedCost;
    private LocalDateTime submittedAt;
    private CustomerDto customer;
    private PropertyDto property;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public CustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	public PropertyDto getProperty() {
		return property;
	}
	public void setProperty(PropertyDto property) {
		this.property = property;
	}

    
}