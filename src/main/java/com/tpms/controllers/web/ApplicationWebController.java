package com.tpms.controllers.web;

import com.tpms.dto.CustomerDto;
import com.tpms.dto.PermitTypeDto;
import com.tpms.dto.PropertyDto;
import com.tpms.entities.Application;
import com.tpms.services.ApplicationService;
import com.tpms.services.CustomerService;
import com.tpms.services.PermitTypeService;
import com.tpms.services.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/staff/applications")
public class ApplicationWebController {

    private final ApplicationService applicationService;
    private final CustomerService customerService;
    private final PropertyService propertyService;
    private final PermitTypeService permitTypeService;

    public ApplicationWebController(ApplicationService applicationService, CustomerService customerService, PropertyService propertyService, PermitTypeService permitTypeService) {
        this.applicationService = applicationService;
        this.customerService = customerService;
        this.propertyService = propertyService;
        this.permitTypeService = permitTypeService;
    }

    @GetMapping("/new")
    public String showNewApplicationForm(Model model) {
        List<CustomerDto> allCustomers = customerService.getAllCustomerDto();
        List<PropertyDto> allProperties = propertyService.getAllPropertyDtos();
        List<PermitTypeDto> allPermitTypes = permitTypeService.getAllPermitTypeDto();

        model.addAttribute("application", new Application());
        model.addAttribute("allCustomers", allCustomers);
        model.addAttribute("allProperties", allProperties);
        model.addAttribute("allPermitTypes", allPermitTypes);

        return "staff/application-form";
    }

    @GetMapping("/{id}/edit")
    public String showEditApplicationForm(@PathVariable Long id, Model model) {
        Application application = applicationService.findApplicationEntityById(id);

        List<CustomerDto> allCustomers = customerService.getAllCustomerDto();
        List<PropertyDto> allProperties = propertyService.getAllPropertyDtos();
        List<PermitTypeDto> allPermitTypes = permitTypeService.getAllPermitTypeDto();

        model.addAttribute("application", application);
        model.addAttribute("allCustomers", allCustomers);
        model.addAttribute("allProperties", allProperties);
        model.addAttribute("allPermitTypes", allPermitTypes);

        return "staff/application-form";
    }

    @PostMapping("/save")
    public String saveApplication(@ModelAttribute("application") Application application) {
        applicationService.addApplication(application);
        return "redirect:/staff/dashboard";
    }
    
    
    @GetMapping("/{id}/delete")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplicationById(id);
        // After deleting, redirect back to the dashboard to see the updated list
        return "redirect:/staff/dashboard";
    }
}