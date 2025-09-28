package com.tpms.controllers.web;

import com.tpms.dto.ApplicationDto;
import com.tpms.services.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/staff") // Base URL for all methods in this controller
public class DashboardController {

    private final ApplicationService applicationService;

    public DashboardController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // 1. Fetch the data from the service layer
        List<ApplicationDto> applicationList = applicationService.getAllApplicationDtos();

        // 2. Add the data to the model with the name "applications"
        // This name MUST match the variable in the th:each loop in dashboard.html
        model.addAttribute("applications", applicationList);

        // 3. Return the path to the HTML template
        return "staff/dashboard";
    }
}