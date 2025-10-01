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

import com.tpms.dto.StaffAccountsDto;
import com.tpms.entities.StaffAccounts;
import com.tpms.services.StaffAccountsService;

@RestController
@RequestMapping("/api/staff-accounts")
public class StaffAccountController {

	private final StaffAccountsService staffAccountsService;
	
	public StaffAccountController(StaffAccountsService staffAccountsService) {
		this.staffAccountsService = staffAccountsService;
	}
	
	//Fetch all StaffAccounts
	@GetMapping("/")
	public ResponseEntity<List<StaffAccountsDto>> getAllStaffAccounts(){
		List<StaffAccountsDto> list =  this.staffAccountsService.getAllStaffAccountsDtos();
		return ResponseEntity.ok(list);
	}
	
	//Fetch By Id
	@GetMapping("/{id}")
	public ResponseEntity<StaffAccountsDto> getStaffAccountDto(@PathVariable Long id){
		StaffAccountsDto staffAccount = this.staffAccountsService.getStaffAccountDtoById(id);
		return ResponseEntity.ok(staffAccount);
	}
	
	//add StaffAccount
	@PostMapping("/")
	public ResponseEntity<StaffAccountsDto> addStaffAccount(@RequestBody StaffAccounts staffAccount){
		StaffAccountsDto newStaffAccount =  this.staffAccountsService.addStaffAccount(staffAccount);
		return ResponseEntity.status(HttpStatus.CREATED).body(newStaffAccount);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StaffAccountsDto> updateStaffAccounts(@PathVariable Long id, @RequestBody StaffAccounts staffAccount){
		StaffAccountsDto updatedStaffAccount = this.staffAccountsService.updateStaffAccountById(id, staffAccount);
		// in StaffAccountsServce i have changed this method to updateStaffAccount 
		return ResponseEntity.ok(updatedStaffAccount);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStaffAccount(@PathVariable Long id){
		this.staffAccountsService.deleteStaffAccountById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{staffId}/roles/{roleId}")
	public ResponseEntity<StaffAccountsDto> assignRole(@PathVariable Long staffId, @PathVariable Long roleId) {
	    StaffAccountsDto updatedStaffAccount = staffAccountsService.assignRoleToStaff(staffId, roleId);
	    return ResponseEntity.ok(updatedStaffAccount);
	}
	
	
}
