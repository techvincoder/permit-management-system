package com.tpms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tpms.dao.CouncilRepository;
import com.tpms.entities.Council;

@Service
public class CouncilService {

	private final CouncilRepository councilRepository;

	public CouncilService(CouncilRepository councilRepository) {
		this.councilRepository = councilRepository;
	}
	
	
	public Council addCouncil(Council council){
		Council c =  this.councilRepository.save(council);
		return c;
	}
	
	public List<Council> getAllCouncils(){
		List <Council> list = (List<Council>)this.councilRepository.findAll();
		return list;
	}
	
	public Optional<Council> getCouncilById(Long id) {
		Optional<Council> c = this.councilRepository.findById(id);
		if(c.isPresent()) {
			return c;
		}else {
			throw new RuntimeException("There is no Council with the Id: " + id);
		}
	}
	
	public Council updateCouncilById(long id, Council council){
		return councilRepository.findById(id).map(t -> {
			t.setAddress(council.getAddress());
			t.setContactEmail(council.getContactEmail());
			t.setContactPhone(council.getContactPhone());
			//More Getters/Setters in future if needed
			return councilRepository.save(t);
		}).orElse(null);
	}
	
	public void deleteCouncilById(long id) {
		this.councilRepository.deleteById(id);
	}
	
	
	
}
