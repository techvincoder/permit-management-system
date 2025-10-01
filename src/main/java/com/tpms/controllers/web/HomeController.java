package com.tpms.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // This method handles requests to the root URL of your application (e.g., http://localhost:8080/)
    @GetMapping("/")
    public String showHomePage() {
        return "home"; // This tells Thymeleaf to render the home.html template
    }
}