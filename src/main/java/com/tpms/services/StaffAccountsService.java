package com.tpms.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.tpms.dao.StaffAccountsRepository;
import com.tpms.entities.StaffAccounts;
import com.tpms.exceptions.ResourceNotFoundException;

@Service
public class StaffAccountsService {

	private final StaffAccountsRepository staffAccountsRepository;
	
	public StaffAccountsService(StaffAccountsRepository staffAccountsRepository) {
		this.staffAccountsRepository = staffAccountsRepository;
	}
	
	//Add StaffAccount
	public StaffAccounts addStaffAccount(StaffAccounts staffAccount) {
		return this.staffAccountsRepository.save(staffAccount);
	}
	
	//Fetch all
	public List<StaffAccounts> getAllStaffAccounts(){
		List<StaffAccounts> list = this.staffAccountsRepository.findAll();
		return list;
	}
	
	//Fetch By ID
	public StaffAccounts getStaffAccountById(long id) {
		return this.staffAccountsRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is no Staff Account with ID: "+id));
	}
	
	//Update by ID
	public StaffAccounts updateStaffAccountById(long id, StaffAccounts staffAccounts) {
		StaffAccounts account = this.staffAccountsRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is no User with this details: "+id));
		
		//Getters and Setters
		account.setEmail(staffAccounts.getEmail());
		//add more getters and setters
		
		return this.staffAccountsRepository.save(account);
	}
	
	//Delete by ID
	public void deleteStaffAccountById(long id) {
		if(! this.staffAccountsRepository.existsById(id)) {
			throw new ResourceNotFoundException("There is no Staff Account with ID: "+id);
		}
	}
}
