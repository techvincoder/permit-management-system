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
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"council", "application"})
@Table(name="property")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="property_id")
	private Long id;
	
	@Column(name="full_address")
	private String fullAddress;
	
	@Column(name="street_name")
	private String streetName;
	
	private String city;
	
	private String state;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="parcel_number")
	private String parcelNumber;
	
	@ManyToOne
	@JoinColumn(name="council_id")
	//@JsonBackReference
	private Council council;
	
	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
	//@JsonManagedReference
	private Set<Application> application = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getParcelNumber() {
		return parcelNumber;
	}

	public void setParcelNumber(String parcelNumber) {
		this.parcelNumber = parcelNumber;
	}

	public Council getCouncil() {
		return council;
	}

	public void setCouncil(Council council) {
		this.council = council;
	}

	public Set<Application> getApplication() {
		return application;
	}

	public void setApplication(Set<Application> application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", fullAddress=" + fullAddress + ", streetName=" + streetName + ", city=" + city
				+ ", state=" + state + ", postalCode=" + postalCode + ", parcelNumber=" + parcelNumber + ", council="
				+ council + ", application=" + application + "]";
	}

	public Property(Long id, String fullAddress, String streetName, String city, String state, String postalCode,
			String parcelNumber, Council council, Set<Application> application) {
		super();
		this.id = id;
		this.fullAddress = fullAddress;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.parcelNumber = parcelNumber;
		this.council = council;
		this.application = application;
	}

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
