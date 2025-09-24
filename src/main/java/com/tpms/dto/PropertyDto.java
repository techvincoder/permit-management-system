package com.tpms.dto;

public class PropertyDto {
    private Long id;
    private String fullAddress;
    private String city;
    private String state;
    private String postalCode;
    private String parcelNumber;
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

    
}