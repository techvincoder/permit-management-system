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
import com.tpms.entities.PermitType;
import com.tpms.services.PermitTypeService;

@RestController
@RequestMapping("/api/permit-type")
public class PermitTypeController {

	
private final PermitTypeService permitTypeService;
	
	public PermitTypeController(PermitTypeService permitTypeService) {
		this.permitTypeService = permitTypeService;
	}
	
	//Get all Details
	@GetMapping("/")
	public ResponseEntity<List<PermitType>> getAllPermitType(){
		List<PermitType> list = this.permitTypeService.getAllPermitType();
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<PermitType> getPermitType(@PathVariable Long id){
		PermitType permitType = this.permitTypeService.getPermitTypeById(id);
		return ResponseEntity.ok(permitType);
	}
	
	@PostMapping("/")
	public ResponseEntity<PermitType> createPermitType(@RequestBody PermitType permitType){
		PermitType newPermitType = this.permitTypeService.addPermitType(permitType);
		return ResponseEntity.status(HttpStatus.CREATED).body(newPermitType);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PermitType> updatePermitType(@PathVariable Long id , @RequestBody PermitType permitType){
		PermitType newPermitType = this.permitTypeService.getPermitTypeById(id);
		return ResponseEntity.ok(newPermitType);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
		this.permitTypeService.deletePermitTypeById(id);
		return ResponseEntity.noContent().build();
	}
}
