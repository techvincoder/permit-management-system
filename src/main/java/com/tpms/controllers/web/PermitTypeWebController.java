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
import com.tpms.dto.PermitTypeDto;
import com.tpms.entities.PermitType;
import com.tpms.services.CouncilService;
import com.tpms.services.PermitTypeService;

@Controller
@RequestMapping("/staff/permit-types")
public class PermitTypeWebController {
	
	private final PermitTypeService permitTypeService;
	private final CouncilService councilService;
	
	public PermitTypeWebController(PermitTypeService permitTypeService, CouncilService councilService) {
		this.permitTypeService = permitTypeService;
		this.councilService = councilService;
	}

	@GetMapping("")
	public String showPermitTypesList(Model model) {
		
		List<PermitTypeDto> permitTypeList = this.permitTypeService.getAllPermitTypeDto();
		model.addAttribute("permitTypes", permitTypeList);
		return "staff/permit-types-list";
	}
	@GetMapping("/new")
	public String createPermitType(Model model) {
        List<CouncilDto> allCouncils = councilService.getAllCouncilDtos();

        model.addAttribute("permitType", new PermitType());
        model.addAttribute("allCouncils", allCouncils); 
		return "staff/permit-types-form";
	}
	
	@PostMapping("/save")
	public String savePermitType(@ModelAttribute PermitType permitType) {
		permitTypeService.addPermitType(permitType);
		return "redirect:/staff/permit-types";
	}
	
	@GetMapping("/{id}/edit")
	public String editPermitType(@PathVariable Long id, Model model) {
		
		PermitType pt = this.permitTypeService.getPermitTypeEntityById(id);
		List<CouncilDto> councilDtoList = this.councilService.getAllCouncilDtos();
		
		model.addAttribute("permitType", pt);
		model.addAttribute("allCouncils", councilDtoList);
		
		return "staff/permit-types-form";
		
	}
	
	@GetMapping("/{id}/delete")
	public String deletePermitType(@PathVariable Long id) {
		permitTypeService.deletePermitTypeById(id);
		return "redirect:/staff/permit-types";
		
	}
}
