package com.tpms.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="staff_account")
public class StaffAccounts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="staff_account_id")
	private Long id;
	
	@Column(unique = true)
	private String email;

	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name="is_Active")
	private Boolean isActive;
	
	@OneToMany(mappedBy = "assignedStaff", cascade = CascadeType.ALL, orphanRemoval = true)
	//@JsonManagedReference
	private Set<Application>application = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "council_id")
	//@JsonBackReference
	private Council council;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="staff_account_role",
			joinColumns = @JoinColumn(name = "staff_account_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
			)
	//@JsonIgnore
	private Set<Role> role = new HashSet<>();
	
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Application> getApplication() {
		return application;
	}

	public void setApplication(Set<Application> application) {
		this.application = application;
	}

	public Council getCouncil() {
		return council;
	}

	public void setCouncil(Council council) {
		this.council = council;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
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
		return "StaffAccounts [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", jobTitle=" + jobTitle + ", isActive=" + isActive + ", application="
				+ application + ", council=" + council + ", role=" + role + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

	public StaffAccounts(Long id, String email, String password, String firstName, String lastName, String jobTitle,
			Boolean isActive, Set<Application> application, Council council, Set<Role> role, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.isActive = isActive;
		this.application = application;
		this.council = council;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public StaffAccounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	
	
	
}
