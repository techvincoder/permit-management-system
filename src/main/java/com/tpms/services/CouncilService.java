package com.tpms.services;

import com.tpms.dao.CouncilRepository;
import com.tpms.entities.Council;
import com.tpms.exceptions.ResourceNotFoundException; 
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouncilService {

    private final CouncilRepository councilRepository;

    public CouncilService(CouncilRepository councilRepository) {
        this.councilRepository = councilRepository;
    }

    // Adding the Council
    public Council addCouncil(Council council) {
        return this.councilRepository.save(council);
    }

    // Fetching All Councils 
    public List<Council> getAllCouncils() {
        return this.councilRepository.findAll();
    }

    // Fetching Specific council by Id 
    public Council getCouncilById(Long id) {
        return this.councilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Council not found with id: " + id));
    }

    // Update Council details by Id (Improved)
    public Council updateCouncilById(long id, Council councilDetails) {
        Council existingCouncil = this.councilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Council not found with id: " + id));

        existingCouncil.setContactEmail(councilDetails.getContactEmail());
        existingCouncil.setContactPhone(councilDetails.getContactPhone());

        return councilRepository.save(existingCouncil);
    }

    // Delete Council details by Id 
    public void deleteCouncilById(long id) {
       
        if (!councilRepository.existsById(id)) {
            throw new ResourceNotFoundException("Council not found with id: " + id);
        }
        this.councilRepository.deleteById(id);
    }
}