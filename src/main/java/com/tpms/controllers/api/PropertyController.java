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
	public ResponseEntity<List<Property>> getAllProperties(){
		List<Property> properties = this.propertyService.getAllProperty();
		return ResponseEntity.ok(properties);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Property> getProperty(@PathVariable Long id){
		Property property = this.propertyService.getPropertyById(id);
		return ResponseEntity.ok(property);
	}
	
	@PostMapping("/")
	public ResponseEntity<Property> createProperty(@RequestBody Property property){
		Property newProperty = this.propertyService.addProperty(property);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProperty);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Property> updateProperty(@PathVariable Long id , @RequestBody Property property){
		Property newProperty = this.propertyService.getPropertyById(id);
		return ResponseEntity.ok(newProperty);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProperty(@PathVariable Long id){
		this.propertyService.deletePropertyById(id);
		return ResponseEntity.noContent().build();
	}
	
}
