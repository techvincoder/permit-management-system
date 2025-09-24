package com.tpms.controllers.api;

import com.tpms.dto.CouncilDto;
import com.tpms.entities.Council;
import com.tpms.services.CouncilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/councils")
public class CouncilController {

    private static final Logger logger = LoggerFactory.getLogger(CouncilController.class);

    private final CouncilService councilService;

    public CouncilController(CouncilService councilService) {
        this.councilService = councilService;
    }

    /**
     * Endpoint to get all councils as DTOs.
     * The GlobalExceptionHandler will catch any unexpected errors.
     */
    @GetMapping("/")
    public ResponseEntity<List<CouncilDto>> getAllCouncilDtos() {
        logger.info("Request received to fetch all Council details");
        List<CouncilDto> list = councilService.getAllCouncilDtos();
        return ResponseEntity.ok(list);
    }

    /**
     * Endpoint to get a single council by ID as a DTO.
     * The GlobalExceptionHandler will catch ResourceNotFoundException.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CouncilDto> getCouncilDtoById(@PathVariable Long id) {
        logger.info("Request received for Council with ID: {}", id);
        CouncilDto councilDto = councilService.getCouncilDtoById(id);
        return ResponseEntity.ok(councilDto);
    }

    /**
     * Endpoint to create a new council.
     * The GlobalExceptionHandler will catch ResourceAlreadyExistsException.
     */
    @PostMapping("/")
    public ResponseEntity<CouncilDto> addCouncil(@RequestBody Council council) {
        logger.info("Request received to create new council with name: {}", council.getName());
        CouncilDto newCouncilDto = councilService.addCouncil(council);
        return new ResponseEntity<>(newCouncilDto, HttpStatus.CREATED);
    }

    /**
     * Endpoint to update an existing council.
     * The GlobalExceptionHandler will catch ResourceNotFoundException.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CouncilDto> updateCouncil(@PathVariable Long id, @RequestBody Council council) {
        logger.info("Request received to update council with ID: {}", id);
        CouncilDto updatedCouncilDto = councilService.updateCouncilById(id, council);
        return ResponseEntity.ok(updatedCouncilDto);
    }

    /**
     * Endpoint to delete a council by its ID.
     * The GlobalExceptionHandler will catch ResourceNotFoundException.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCouncil(@PathVariable Long id) {
        logger.info("Request received to delete council with ID: {}", id);
        councilService.deleteCouncilById(id);
        return ResponseEntity.noContent().build();
    }
}
