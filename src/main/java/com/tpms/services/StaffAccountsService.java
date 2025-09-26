package com.tpms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tpms.dao.RoleRepository;
import com.tpms.dao.StaffAccountsRepository;
import com.tpms.dto.StaffAccountsDto;
import com.tpms.entities.Role;
import com.tpms.entities.StaffAccounts;
import com.tpms.exceptions.ResourceAlreadyExistsException;
import com.tpms.exceptions.ResourceNotFoundException;

@Service
public class StaffAccountsService {

	private final StaffAccountsRepository staffAccountsRepository;
	private final RoleRepository roleRepository;
	
	
	public StaffAccountsService(StaffAccountsRepository staffAccountsRepository, RoleRepository roleRepository) {
		this.staffAccountsRepository = staffAccountsRepository;
		this.roleRepository = roleRepository;
	}
	
	//Add StaffAccount
	public StaffAccountsDto addStaffAccount(StaffAccounts staffAccount) {
		Optional<StaffAccounts> existingAccount = staffAccountsRepository.findByEmail(staffAccount.getEmail());
		if (existingAccount.isPresent()) {
		    throw new ResourceAlreadyExistsException("Staff account with email '" + staffAccount.getEmail() + "' already exists.");
		}
		StaffAccounts newStaffAccount =  this.staffAccountsRepository.save(staffAccount);
		return convertToDto(newStaffAccount);
	}
	
	//Fetch all
	public List<StaffAccountsDto> getAllStaffAccountsDtos(){
		List<StaffAccounts> list = this.staffAccountsRepository.findAll();
		List<StaffAccountsDto> lists = new ArrayList<>();
		for(StaffAccounts staffAccount : list) {
			lists.add(convertToDto(staffAccount));
		}
		
		return lists;
	}
	
	//Fetch By ID
	public StaffAccountsDto getStaffAccountDtoById(long id) {
		StaffAccounts staffAccounts = this.staffAccountsRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is no Staff Account with ID: "+id));
		
		return convertToDto(staffAccounts);
	}
	
	//Update by ID
	public StaffAccountsDto updateStaffAccountById(long id, StaffAccounts staffAccounts) {
		StaffAccounts account = this.staffAccountsRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is no User with this details: "+id));
		
		//Getters and Setters
		account.setEmail(staffAccounts.getEmail());
		//add more getters and setters
		
		StaffAccounts updatedStaffAccount = this.staffAccountsRepository.save(account);
		return convertToDto(updatedStaffAccount);
	}
	
	//Delete by ID
	public void deleteStaffAccountById(long id) {
		if(! this.staffAccountsRepository.existsById(id)) {
			throw new ResourceNotFoundException("There is no Staff Account with ID: "+id);
		}
		this.staffAccountsRepository.deleteById(id);
	}
	
	public StaffAccountsDto convertToDto(StaffAccounts staffAccounts) {
		StaffAccountsDto staffAccount = new StaffAccountsDto();
		staffAccount.setId(staffAccounts.getId());
		staffAccount.setFirstName(staffAccounts.getFirstName());
		staffAccount.setLastName(staffAccounts.getLastName());
		staffAccount.setEmail(staffAccounts.getEmail());
		staffAccount.setJobTitle(staffAccounts.getJobTitle());
		staffAccount.setRoles(staffAccounts.getRole());
		return staffAccount;
	}
	
	// Add this method inside your StaffAccountService.java

	public StaffAccountsDto assignRoleToStaff(Long staffId, Long roleId) {
	    // Find the staff account or throw an exception
	    StaffAccounts staffAccount = staffAccountsRepository.findById(staffId)
	            .orElseThrow(() -> new ResourceNotFoundException("StaffAccount not found with ID: " + staffId));

	    // Find the role or throw an exception
	    Role role = roleRepository.findById(roleId)
	            .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + roleId));

	    // Add the role to the staff member's set of roles
	    staffAccount.getRole().add(role);

	    // Save the updated staff member
	    StaffAccounts updatedStaffAccount = staffAccountsRepository.save(staffAccount);

	    // Return the DTO
	    return convertToDto(updatedStaffAccount);
	}
}
