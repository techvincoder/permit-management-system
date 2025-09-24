package com.tpms.controllers.api;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpms.dto.PropertyDto;
import com.tpms.entities.Property;
import com.tpms.services.PropertyService;


@RestController
@RequestMapping("/api/properties")
public class PropertyController {

private final PropertyService propertyService;
	
	public PropertyController(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	//Get all Details
	@GetMapping("/")
	public ResponseEntity<List<PropertyDto>> getAllProperties(){
		List<PropertyDto> properties = (List<PropertyDto>)this.propertyService.getAllPropertyDtos();
		return ResponseEntity.ok(properties);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<PropertyDto> getProperty(@PathVariable Long id){
		PropertyDto propertyDto = this.propertyService.getPropertyDtoById(id);
		return ResponseEntity.ok(propertyDto);
	}
	
	@PostMapping("/")
	public ResponseEntity<PropertyDto> createProperty(@RequestBody Property property){
		PropertyDto newProperty = this.propertyService.addProperty(property);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProperty);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PropertyDto> updateProperty(@PathVariable Long id , @RequestBody Property property){
		PropertyDto newProperty = this.propertyService.updatePropertyById(id);
		return ResponseEntity.ok(newProperty);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProperty(@PathVariable Long id){
		this.propertyService.deletePropertyById(id);
		return ResponseEntity.noContent().build();
	}
	
}
