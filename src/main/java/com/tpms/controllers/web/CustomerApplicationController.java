package com.tpms.controllers.web;

import com.tpms.dto.PermitTypeDto;
import com.tpms.dto.PropertyDto;
import com.tpms.entities.Application;
import com.tpms.entities.Customer;
import com.tpms.entities.PermitType;
import com.tpms.entities.Property;
import com.tpms.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/my-applications")
public class CustomerApplicationController {

    private final ApplicationService applicationService;
    private final CustomerService customerService;
    private final PropertyService propertyService;
    private final PermitTypeService permitTypeService;

    public CustomerApplicationController(ApplicationService applicationService, CustomerService customerService, PropertyService propertyService, PermitTypeService permitTypeService) {
        this.applicationService = applicationService;
        this.customerService = customerService;
        this.propertyService = propertyService;
        this.permitTypeService = permitTypeService;
    }

    @GetMapping("/new")
    public String showNewApplicationForm(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String customerEmail = userDetails.getUsername();

        // Fetch lists for the dropdowns
        List<PropertyDto> customerProperties = propertyService.getAllPropertyDtosByCustomerEmail1(customerEmail);
        List<PermitTypeDto> allPermitTypes = permitTypeService.getAllPermitTypeDto();

        model.addAttribute("application", new Application());
        model.addAttribute("customerProperties", customerProperties);
        model.addAttribute("allPermitTypes", allPermitTypes);

        return "customer/customer-application-form";
    }

    @PostMapping("/save")
    public String saveApplication(@ModelAttribute("application") Application application, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String customerEmail = userDetails.getUsername();

        // SECURITY: Fetch the full, logged-in customer entity to ensure a user cannot submit on behalf of someone else.
        Customer loggedInCustomer = customerService.findCustomerEntityByEmail(customerEmail);
        application.setCustomer(loggedInCustomer);

        // Re-attach other full entities from the form's submitted IDs
        if (application.getProperty() != null && application.getProperty().getId() != null) {
            Property fullProperty = propertyService.findPropertyEntityById(application.getProperty().getId());
            application.setProperty(fullProperty);
        }
        if (application.getPermitType() != null && application.getPermitType().getId() != null) {
            PermitType fullPermitType = permitTypeService.getPermitTypeEntityById(application.getPermitType().getId());
            application.setPermitType(fullPermitType);
        }
        
        // Set default values
        application.setSubmittedAt(LocalDateTime.now());
        application.setStatus(com.tpms.entities.Status.PENDING);
        application.setApplicationNo("APP-" + System.currentTimeMillis()); // Auto-generate a simple application number

        applicationService.addApplication(application);

        return "redirect:/my-applications";
    }
}