package com.tpms.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tpms.dao.ApplicationRepository;
import com.tpms.entities.Application;
import com.tpms.exceptions.ResourceNotFoundException;

@Service
public class ApplicationService {

	private final ApplicationRepository applicationRepository ;

	public ApplicationService(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}
	
	//add Application
	public Application addApplication(Application application) {
		return this.applicationRepository.save(application);
	}
	
	//Fetch All Application
	public List<Application> getAllApplication(){
		List<Application> list = (List<Application>)this.applicationRepository.findAll();
		return list;
	}
	
	//Fetch Application by ID
	public Application getApplicationById(long id) {
		return this.applicationRepository.findById(id)
				.orElseThrow (()-> new ResourceNotFoundException("There is no Application with the Application Id: " +id));
	}
	
	//Update Application by Id
	public Application updateApplicationById(long id, Application application) {
		Application app = this.applicationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("There is no application with the Application ID: " +id));
		app.setDetails(application.getDetails());
		//will add more functions 
		return this.applicationRepository.save(app);
	}
	
	//Delete Application by ID
	public void deleteById(long id) {
		if(!this.applicationRepository.existsById(id)) {
			throw new ResourceNotFoundException("There is no application with the ID: "+ id);
		}
		this.applicationRepository.deleteById(id);
	}
	
	
}
