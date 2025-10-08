package com.tpms.controllers.web;

import com.tpms.dto.ApplicationDto;
import com.tpms.services.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/staff") 
public class DashboardController {

    private final ApplicationService applicationService;

    public DashboardController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
       
        List<ApplicationDto> applicationList = applicationService.getAllApplicationDtos();

        
        model.addAttribute("applications", applicationList);

        // Return the path to the HTML template
        return "staff/dashboard";
    }
}