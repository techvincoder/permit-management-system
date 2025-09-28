package com.tpms.services;

import com.tpms.dao.CouncilRepository;
import com.tpms.dto.CouncilDto;
import com.tpms.entities.Council;
import com.tpms.exceptions.ResourceAlreadyExistsException;
import com.tpms.exceptions.ResourceNotFoundException; 
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CouncilService {

    private final CouncilRepository councilRepository;


    public CouncilService(CouncilRepository councilRepository) {
        this.councilRepository = councilRepository;
    }

    // Adding the Council
    public CouncilDto addCouncil(Council council) {
    	Optional<Council> existingCouncil = councilRepository.findByName(council.getName());
    	
    	if(existingCouncil.isPresent()) {
    		throw new ResourceAlreadyExistsException("Council already exists");
    	}
        Council newCouncil =  this.councilRepository.save(council);
        return convertToDto(newCouncil);
    }

    // Fetching All Councils 
    public List<CouncilDto> getAllCouncilDtos() {
        List<Council> list = this.councilRepository.findAll();
        List<CouncilDto> councilDtoList = new ArrayList<>();
        
        for(Council council: list) {
        	councilDtoList.add(convertToDto(council));
        }
        
        return councilDtoList;
    }

    // Fetching Specific council by Id 
    public CouncilDto getCouncilDtoById(Long id) {
        Council council = this.councilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Council not found with id: " + id));
        return convertToDto(council);
    }

    // Update Council details by Id (Improved)
    public CouncilDto updateCouncilById(long id, Council councilDetails) {
        Council existingCouncil = this.councilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Council not found with id: " + id));

        existingCouncil.setContactEmail(councilDetails.getContactEmail());
        existingCouncil.setContactPhone(councilDetails.getContactPhone());

        Council updatedCouncil =  councilRepository.save(existingCouncil);
        return convertToDto(updatedCouncil);
    }

    // Delete Council details by Id 
    public void deleteCouncilById(long id) {
       
        if (!councilRepository.existsById(id)) {
            throw new ResourceNotFoundException("Council not found with id: " + id);
        }
        this.councilRepository.deleteById(id);
    }
    
    //For updating the Council with Edit
    public Council findCouncilEntityById(Long id) {
        return councilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Council not found with ID: " + id));
    }
    
    
    private CouncilDto convertToDto(Council council) {
    	CouncilDto dto = new CouncilDto();
        dto.setId(council.getId());
        dto.setName(council.getName());
        dto.setAddress(council.getAddress());
        dto.setWebsite(council.getWebsite());
        dto.setContactEmail(council.getContactEmail());
        dto.setContactPhone(council.getContactPhone());
        dto.setActive(council.getActive());
        return dto;
    }
}
	