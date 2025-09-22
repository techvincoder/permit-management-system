package com.tpms.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "permit_type")
public class PermitType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = " permit_type_id")
	private Long id;
	
	private String name ;
	
	@ManyToOne
	@JoinColumn(name = "council_id")
	private Council council;

	@OneToMany(mappedBy = "permitType", cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Application> applications = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Council getCouncil() {
		return council;
	}

	public void setCouncil(Council council) {
		this.council = council;
	}

	public Set<Application> getApplications() {
		return applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

	@Override
	public String toString() {
		return "PermitType [id=" + id + ", name=" + name + ", council=" + council + ", applications=" + applications
				+ "]";
	}

	public PermitType(Long id, String name, Council council, Set<Application> applications) {
		super();
		this.id = id;
		this.name = name;
		this.council = council;
		this.applications = applications;
	}

	public PermitType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
