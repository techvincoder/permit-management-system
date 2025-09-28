package com.tpms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tpms.dao.PermitTypeRepository;
import com.tpms.dto.PermitTypeDto;
import com.tpms.entities.Council;
import com.tpms.entities.PermitType;
import com.tpms.exceptions.ResourceNotFoundException;

@Service
public class PermitTypeService {

	private final PermitTypeRepository permitTypeRepository;
	
	public PermitTypeService(PermitTypeRepository permitTypeReposiTory) {
		this.permitTypeRepository = permitTypeReposiTory;
	}
	
	//add PermitTypeController
	public PermitType addPermitType(PermitType permitType) {
		return this.permitTypeRepository.save(permitType);
	}
	
	//Fetch All
	public List<PermitTypeDto> getAllPermitTypeDto(){
		List<PermitType> list =(List<PermitType>) this.permitTypeRepository.findAll();
		List<PermitTypeDto> dtoList = new ArrayList<>();
		
		for(PermitType dto: list) {
			dtoList.add(convertToDto(dto));
		}
		
		return dtoList;
	}
	
	
	//Fetch DTO By Id
	public PermitTypeDto getPermitTypeDtoById(long id) {
		 PermitType pt =  this.permitTypeRepository.findById(id)
				 .orElseThrow(()-> new ResourceNotFoundException("There is no permit type with the ID: "+id));
		 return convertToDto(pt);
	}
	
	//fetch raw Entity by Id
	public PermitType getPermitTypeEntityById(Long id ) {
		return this.permitTypeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("There is no Permit Type with the ID: " + id));
	}
	
	//Update By ID
	public PermitType updatePermitTypeById(long id, PermitType permitType) {
		PermitType permit = this.permitTypeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is no Permit Type with the ID: "+id));
		
		//GETTERS AND SETTERS
		permit.setName(permitType.getName());
		//add more setters and getters
		return this.permitTypeRepository.save(permit);
	}
	
	//Delete By Id
	public void deletePermitTypeById(long id) {
		if( ! this.permitTypeRepository.existsById(id)) {
			throw new ResourceNotFoundException("There is no such Permit Type with ID: "+id);
		}
		
		this.permitTypeRepository.deleteById(id);
	}
	
	//for getting the council from the DB of the PermitType
	public Council getCouncilByPermitId(Long id) {
		Council council = this.permitTypeRepository.getCouncilByPermitTypeId(id);
		return council;
	}
	
	public PermitTypeDto convertToDto(PermitType permitType) {
		
		PermitTypeDto pt = new PermitTypeDto();
		
		pt.setId(permitType.getId());
		pt.setName(permitType.getName());
		pt.setCouncil(permitType.getCouncil());
		return pt;
	}
}
