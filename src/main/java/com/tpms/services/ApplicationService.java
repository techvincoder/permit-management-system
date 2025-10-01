package com.tpms.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.tpms.dao.ApplicationRepository;
import com.tpms.dto.ApplicationDto;
import com.tpms.dto.CustomerDto;
import com.tpms.dto.PropertyDto;
import com.tpms.entities.Application;
import com.tpms.exceptions.ResourceNotFoundException;

@Service
public class ApplicationService {

	private final ApplicationRepository applicationRepository ;

	public ApplicationService(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}
	
	//add Application
	public ApplicationDto addApplication(Application application) {
		Application newApplication = this.applicationRepository.save(application);
		return convertToDto(newApplication);
	}
	
	//Fetch All Application
	public List<ApplicationDto> getAllApplicationDtos(){
		List<Application> list = (List<Application>)this.applicationRepository.findAll();
		List<ApplicationDto> applicationDtoList = new ArrayList<>();
		
		for(Application application: list) {
			applicationDtoList.add(convertToDto(application));
		}
		return applicationDtoList;
	}
	
	//Fetch Application by ID
	public ApplicationDto getApplicationDtoById(long id) {
		Application application = this.applicationRepository.findById(id)
				.orElseThrow (()-> new ResourceNotFoundException("There is no Application with the Application Id: " +id));
		return convertToDto(application);
	}
	
	//Update Application by Id
	public ApplicationDto updateApplicationById(long id, Application application) {
		Application app = this.applicationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("There is no application with the Application ID: " +id));
		app.setDetails(application.getDetails());
		//will add more functions 
		Application updatedApplication = this.applicationRepository.save(app);
		return convertToDto(updatedApplication);
	}
	
	//Delete Application by ID
	public void deleteApplicationById(long id) {
		if(!this.applicationRepository.existsById(id)) {
			throw new ResourceNotFoundException("There is no application with the ID: "+ id);
		}
		this.applicationRepository.deleteById(id);
	}
	
	
	
	private ApplicationDto convertToDto(Application app) {
	    ApplicationDto dto = new ApplicationDto();
	    dto.setId(app.getId());
	    dto.setApplicationNo(app.getApplicationNo());
	    dto.setStatus(app.getStatus().toString()); // Convert Enum to String
	    dto.setDetails(app.getDetails());
	    dto.setEstimatedCost(app.getEstimatedCost());
	    dto.setSubmittedAt(app.getSubmittedAt());
	    
	    // Create and set nested DTOs
	    if (app.getCustomer() != null) {
	        CustomerDto customerDto = new CustomerDto();
	        customerDto.setId(app.getCustomer().getId());
	        customerDto.setFirstName(app.getCustomer().getFirstName());
	        customerDto.setLastName(app.getCustomer().getLastName());
	        dto.setCustomer(customerDto);
	    }

	    if (app.getProperty() != null) {
	        PropertyDto propertyDto = new PropertyDto();
	        propertyDto.setId(app.getProperty().getId());
	        propertyDto.setFullAddress(app.getProperty().getFullAddress());
	        dto.setProperty(propertyDto);
	    }
	    
	    return dto;
	}
	
	public Application findApplicationEntityById(Long id) {
	    return applicationRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + id));
	}
	
	public List<ApplicationDto> findAllApplicationsByCustomerEmail(String email) {
	    List<Application> applications = applicationRepository.findByCustomerEmail(email);
	    
	    // We can reuse our existing DTO conversion logic
	    List<ApplicationDto> dtos = new ArrayList<>();
	    for (Application application : applications) {
	        dtos.add(convertToDto(application)); // Assuming you have a private convertToDto method
	    }
	    return dtos;
	}
	
	
	
}
