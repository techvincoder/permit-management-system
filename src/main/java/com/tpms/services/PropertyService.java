package com.tpms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tpms.dao.PropertyRepository;
import com.tpms.entities.Property;
import com.tpms.exceptions.ResourceNotFoundException;

@Service
public class PropertyService {

	private final PropertyRepository propertyRepository;
	
	public PropertyService(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}
	
	//Add Property
	public Property addProperty(Property property) {
		return this.propertyRepository.save(property);
	}
	
	//Fetch All
	public List<Property> getAllProperty(){
		List<Property>list = (List<Property>)this.propertyRepository.findAll();
		return list;
	}
	
	//Fetch by Id
	public Property getPropertyById(long id) {
		return this.propertyRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is no Property with ID: "+id));
	}
	
	//update By ID
	public Property updatePropertyById(long id) {
		Property prop = this.propertyRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is No such Property with ID: " +id));
		
		//Getters Setters
		prop.setCity(prop.getCity());
		//more getters and setters
		return this.propertyRepository.save(prop);
	}
	
	//delete by id
	public void deletePropertyById(long id) {
		if( ! this.propertyRepository.existsById(id)) {
			throw new ResourceNotFoundException("There is no Property with ID: " +id);
		}
		
		this.propertyRepository.deleteById(id);
	}
}
