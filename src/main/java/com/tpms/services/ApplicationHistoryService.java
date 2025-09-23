package com.tpms.services;

import com.tpms.dao.ApplicationHistoryRepository;
import com.tpms.entities.ApplicationHistory;
import com.tpms.exceptions.ResourceNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ApplicationHistoryService {

    private final ApplicationHistoryRepository applicationHistoryRepository;

	public ApplicationHistoryService(ApplicationHistoryRepository applicationHistoryRepository) {
		this.applicationHistoryRepository = applicationHistoryRepository;
	}
	
	//add ApplicationHistory
	public ApplicationHistory addApplicationHistory(ApplicationHistory applicationHistory) {
		return this.applicationHistoryRepository.save(applicationHistory);
	}
	
	//fetch All
	public List<ApplicationHistory> getAllApplicationHistory(){
		List<ApplicationHistory> list = (List<ApplicationHistory>)this.applicationHistoryRepository.findAll();
		return list;
	}
	
	//Fetch by Id
	public ApplicationHistory getApplicationHistoryById(long id) {
		return this.applicationHistoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("There is no ApplicationHistory with ID: "+id));
	}
	
	//update by ID
	public ApplicationHistory updateApplicationHistoryById(long id, ApplicationHistory applicationHistory) {
		ApplicationHistory app = this.applicationHistoryRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is no such data with the ID: " +id));
		//getters and setters
		app.setDescription(applicationHistory.getDescription());
		//similarly more can be added
		
		return this.applicationHistoryRepository.save(app);
	}
	
	
	//Delete By ID
	public void deleteApplicationHistoryById(long id) {
		if(!this.applicationHistoryRepository.existsById(id)) {
			throw new ResourceNotFoundException("There is no ApplicationHistory with ID: "+id);
		}
		
		this.applicationHistoryRepository.deleteById(id);
	}
}
