// All the errors are being handled by the GlobalExceptionHandler in the Exceptions package
// but just for the practice some try catch block is their in CouncilService

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

import com.tpms.entities.Application;
import com.tpms.services.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

	private final ApplicationService applicationService;
	
	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Application>> getAllApplications(){
		List<Application> list = this.applicationService.getAllApplication();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Application> getApplicationById(@PathVariable Long id){
		Application application = this.applicationService.getApplicationById(id);
		return ResponseEntity.status(HttpStatus.OK).body(application);
	}
	
	@PostMapping("/")
	public ResponseEntity<Application> createApplication(@RequestBody Application application){
		Application newApplication = this.applicationService.addApplication(application);
		return ResponseEntity.status(HttpStatus.CREATED).body(newApplication);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Application> updateApplication(@PathVariable Long id,@RequestBody Application application){
		Application newApplication = this.applicationService.updateApplicationById(id, application);
		return ResponseEntity.status(HttpStatus.OK).body(newApplication);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteApplication(@PathVariable Long id){
		this.applicationService.deleteApplicationById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
