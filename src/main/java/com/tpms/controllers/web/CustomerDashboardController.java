package com.tpms.controllers.web;

import com.tpms.dto.ApplicationDto;
import com.tpms.services.ApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerDashboardController {

    private final ApplicationService applicationService;

    public CustomerDashboardController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/my-applications")
    public String showMyApplications(Model model, Authentication authentication) {
        // Getting the current logging in users detail
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername(); // This will be the customer's email

        // Fetch applications specifically for the particuular user
        List<ApplicationDto> myApplications = applicationService.findAllApplicationsByCustomerEmail(username);

        //Adding the list to the model
        model.addAttribute("applications", myApplications);

// Returning the customer's dashboard view
        return "customer/my-applications";
    }
}