package com.tpms.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "council")
public class Council {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="council_id")
	private Long id;
	
	private String name;
	
	private String address;
	
	private String website;
	
	private String contactPhone;
	
	private String contactEmail;
	
	private Boolean is_Active;
	
	@OneToMany(mappedBy = "council", cascade = CascadeType.ALL , orphanRemoval = true)
	private Set<Property> property = new HashSet<>();
	
	@OneToMany(mappedBy = "council", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<StaffAccounts> staff_account = new HashSet<>();
	
	@OneToMany(mappedBy = "council", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Application> application = new HashSet<>();
	
	@OneToMany(mappedBy = "council", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PermitType> permit_type = new HashSet<>();

	public Council() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Council(Long id, String name, String address, String website, String contactPhone, String contactEmail,
			Boolean is_Active, Set<Property> property, Set<StaffAccounts> staff_account, Set<Application> application,
			Set<PermitType> permit_type) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.website = website;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.is_Active = is_Active;
		this.property = property;
		this.staff_account = staff_account;
		this.application = application;
		this.permit_type = permit_type;
	}

	@Override
	public String toString() {
		return "Council [id=" + id + ", name=" + name + ", address=" + address + ", website=" + website
				+ ", contactPhone=" + contactPhone + ", contactEmail=" + contactEmail + ", is_Active=" + is_Active
				+ ", property=" + property + ", staff_account=" + staff_account + ", application=" + application
				+ ", permit_type=" + permit_type + "]";
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Boolean getIs_Active() {
		return is_Active;
	}

	public void setIs_Active(Boolean is_Active) {
		this.is_Active = is_Active;
	}

	public Set<Property> getProperty() {
		return property;
	}

	public void setProperty(Set<Property> property) {
		this.property = property;
	}

	public Set<StaffAccounts> getStaff_account() {
		return staff_account;
	}

	public void setStaff_account(Set<StaffAccounts> staff_account) {
		this.staff_account = staff_account;
	}

	public Set<Application> getApplication() {
		return application;
	}

	public void setApplication(Set<Application> application) {
		this.application = application;
	}

	public Set<PermitType> getPermit_type() {
		return permit_type;
	}

	public void setPermit_type(Set<PermitType> permit_type) {
		this.permit_type = permit_type;
	}
	
	
	
	
	
}
