package com.tpms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tpms.dao.ApplicationDocumentRepository;
import com.tpms.entities.ApplicationDocument;
import com.tpms.exceptions.ResourceNotFoundException;

@Service
public class ApplicationDocumentService {

	private final ApplicationDocumentRepository applicationDocumentRepository;
	
	public ApplicationDocumentService(ApplicationDocumentRepository applicationDocumentRepository) {
		this.applicationDocumentRepository = applicationDocumentRepository;
	}
	
	//add ApplicationDocuments
	public ApplicationDocument addApplicationDocument(ApplicationDocument applicationDocument) {
		return this.applicationDocumentRepository.save(applicationDocument);
	}
	
	
	//Fetch All
	public List<ApplicationDocument> getAllApplicationDocument(){
		List<ApplicationDocument> list = (List<ApplicationDocument>)this.applicationDocumentRepository.findAll();
		return list;
	}
	
	//Fetch Details by Id
	public ApplicationDocument getApplicationById(long id) {
		return this.applicationDocumentRepository.findById(id).orElseThrow(()->
			new ResourceNotFoundException("There are no ApplicationDocument with the ID: "+id));
	}
	
	//Update Details by Id
	public ApplicationDocument updateApplicationDocumentById(long id, ApplicationDocument applicationDocument) {
		ApplicationDocument app = this.applicationDocumentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("There are No Application Document with ID: "+ id));
		
		return this.applicationDocumentRepository.save(app);
	}
	
	//Delete by ID
	public void deleteById(long id) {
		if(!this.applicationDocumentRepository.existsById(id)) {
			throw new ResourceNotFoundException("No Record found!!");
		}
		this.applicationDocumentRepository.deleteById(id);
	}
}
