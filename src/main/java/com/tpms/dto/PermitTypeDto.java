package com.tpms.dto;

import com.tpms.entities.Council;

public class PermitTypeDto {

	private Long id;
	private String name;
	private Council council;
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
	
}
