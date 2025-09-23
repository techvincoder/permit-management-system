package com.tpms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tpms.dao.PermitTypeRepository;
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
	public List<PermitType> getAllPermitType(){
		List<PermitType> list =(List<PermitType>) this.permitTypeRepository.findAll();
		return list;
	}
	
	
	//Fetch By Id
	public PermitType getPermitTypeById(long id) {
		 return this.permitTypeRepository.findById(id)
				 .orElseThrow(()-> new ResourceNotFoundException("There is no permit type with the ID: "+id));
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
}
