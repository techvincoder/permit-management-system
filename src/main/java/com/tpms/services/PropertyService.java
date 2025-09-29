package com.tpms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tpms.dao.PropertyRepository;
import com.tpms.dto.PropertyDto;
import com.tpms.entities.Property;
import com.tpms.exceptions.ResourceNotFoundException;

@Service
public class PropertyService {

	private final PropertyRepository propertyRepository;
	
	public PropertyService(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}
	
	//Add Property
	public PropertyDto addProperty(Property property) {
		Property newProperty =  this.propertyRepository.save(property);
		return convertToDto(newProperty);
	}
	
	//Fetch All
	public List<PropertyDto> getAllPropertyDtos(){
		List<Property>list = (List<Property>)this.propertyRepository.findAll();
		List<PropertyDto> listPropertyDto = new ArrayList<>();
		
		for(Property property: list) {
			listPropertyDto.add(convertToDto(property));
		}
		return listPropertyDto;
	}
	
	//Fetch by Id
	public PropertyDto getPropertyDtoById(long id) {
		Property property=  this.propertyRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is no Property with ID: "+id));
		return convertToDto(property);
	}
	
	//update By ID
	public PropertyDto updatePropertyById(long id) {
		Property prop = this.propertyRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is No such Property with ID: " +id));
		
		//Getters Setters
		prop.setCity(prop.getCity());
		//more getters and setters
		Property updatedProperty = this.propertyRepository.save(prop);
		return convertToDto(updatedProperty);
	}
	
	//delete by id
	public void deletePropertyById(long id) {
		if( ! this.propertyRepository.existsById(id)) {
			throw new ResourceNotFoundException("There is no Property with ID: " +id);
		}
		
		this.propertyRepository.deleteById(id);
	}
	
	public PropertyDto convertToDto(Property property) {
		PropertyDto propertyDto = new PropertyDto();
		
		propertyDto.setId(property.getId());
		propertyDto.setCity(property.getCity());
		propertyDto.setFullAddress(property.getFullAddress());
		propertyDto.setParcelNumber(property.getParcelNumber());
		propertyDto.setPostalCode(property.getPostalCode());
		propertyDto.setState(property.getState());
		
		return propertyDto;
	}
	
	public Property findPropertyEntityById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + id));
    }
	
	
}
