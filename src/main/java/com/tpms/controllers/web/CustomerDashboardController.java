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
        // Step 1: Get the currently logged-in user's details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername(); // This will be the customer's email

        // Step 2: Fetch applications specifically for this user
        // We will create this new service method next
        List<ApplicationDto> myApplications = applicationService.findAllApplicationsByCustomerEmail(username);

        // Step 3: Add the list to the model
        model.addAttribute("applications", myApplications);

        // Step 4: Return the customer dashboard view
        return "customer/my-applications";
    }
}