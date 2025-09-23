package com.tpms.controllers.api;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tpms.entities.Council;
import com.tpms.exceptions.ResourceNotFoundException;
import com.tpms.services.CouncilService;

@RestController
@RequestMapping("/api/councils")
public class CouncilController {

	private static final Logger logger = LoggerFactory.getLogger(CouncilController.class);
	
	private final CouncilService councilService;
	
	public CouncilController(CouncilService councilService) {
		this.councilService= councilService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Council>> getAllCouncil(){
		try {
			logger.info("Fetching all Council Details");
			List<Council> list = this.councilService.getAllCouncils();
			return ResponseEntity.status(HttpStatus.OK).body(list);
			
		}catch(Exception e) {
			logger.error("Oops! Something went wrong!! ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Council> getCouncilById(@PathVariable Long id){
		try {
			logger.info("Requested Details for the Coundil with ID: {}" + id);
			Council council = this.councilService.getCouncilById(id);
			return ResponseEntity.status(HttpStatus.OK).body(council);
		}catch(ResourceNotFoundException e) {
			logger.warn("There is no Council with the Id : {} ",id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e) {
			logger.error("oops...Something went wrong!!!", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Council> addCouncil(@RequestBody Council council){
		try {
			Council newCouncil = this.councilService.addCouncil(council);
			return ResponseEntity.status(HttpStatus.CREATED).body(newCouncil);
		}catch(Exception e) {
            logger.error("Error creating new council with name: {}", council.getName(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Council> updateCouncil(@PathVariable Long id,@RequestBody Council council){
		try {
			Council updatedCouncil = this.councilService.updateCouncilById(id,council );
			return ResponseEntity.ok(updatedCouncil);
		}catch(ResourceNotFoundException e) {
			logger.warn("There is no council with the ID: {}",id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e) {
			logger.error("Oops..Something went wrong",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCouncil(@PathVariable Long id) {
		try {
			this.councilService.deleteCouncilById(id);
			return ResponseEntity.noContent().build();
			
		}catch(Exception e) {
			logger.error("oops..Something went wrong!!!",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
}
