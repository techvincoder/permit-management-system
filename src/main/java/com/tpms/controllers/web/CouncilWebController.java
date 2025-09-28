package com.tpms.controllers.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.tpms.dto.CouncilDto;
import com.tpms.entities.Council;
import com.tpms.services.CouncilService;

@Controller
@RequestMapping("/staff/councils")
public class CouncilWebController{
	
	private final CouncilService councilService;
	
	public CouncilWebController(CouncilService councilService) {
		this.councilService = councilService;
	}
	
	@GetMapping("")
	public String showAllCouncilDtos(Model model) {
		List<CouncilDto> allCouncilDtos = (List<CouncilDto>)this.councilService.getAllCouncilDtos();
		
		model.addAttribute("councils",allCouncilDtos);
		
		return "staff/councils-list";
	}
	
	 @GetMapping("/new")
	    public String showNewCouncilForm(Model model) {
	        
	        model.addAttribute("council", new Council());
	        return "staff/council-form";
	    }


	    @PostMapping("/save")
	    public String saveCouncil(@ModelAttribute Council council) {
	        // @ModelAttribute takes the submitted form data and builds a Council object
	        councilService.addCouncil(council); // Use your existing service method to save
	        return "redirect:/staff/councils"; // Redirect back to the list page on success
	    }
	    
	    @GetMapping("/{id}/edit")
	    public String editCouncil(@PathVariable Long id, Model model) { 
	    	Council council = this.councilService.findCouncilEntityById(id);
	    	model.addAttribute("council", council);
	    	
	    	return "staff/council-form";
	    }
	    
	    @GetMapping("/{id}/delete")
	    public String deleteCouncil(@PathVariable Long id) {
	        councilService.deleteCouncilById(id);
	        return "redirect:/staff/councils"; // Redirect back to the list
	    }
	
}