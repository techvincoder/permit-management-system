package com.tpms.controllers.web;

import com.tpms.dto.CustomerDto;
import com.tpms.entities.Customer;
import com.tpms.services.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class CustomerWebController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder; //for hashing passwords

    // The constructor now needs the PasswordEncoder
    public CustomerWebController(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    //  Staff-Facing Methods
    @GetMapping("/staff/customers")
    public String showCustomerList(Model model) {
        List<CustomerDto> customerList = customerService.getAllCustomerDto();
        model.addAttribute("customers", customerList);
        return "staff/customers-list";
    }

    @GetMapping("/staff/customers/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "staff/customer-form";
    }

    @GetMapping("/staff/customers/{id}/edit")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.findCustomerEntityById(id);
        model.addAttribute("customer", customer);
        return "staff/customer-form";
    }

    @PostMapping("/staff/customers/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/staff/customers";
    }

    @GetMapping("/staff/customers/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/staff/customers";
    }

    //Public-Facing Methods ---

    //Shows the public registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/register";
    }

    //Processes the public registration form
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("customer") Customer customer) {
        //  hashing the password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setIsActive(true); // Setting new users to active by default

        customerService.addCustomer(customer);

        // Redirecting to the login page with a success message
        return "redirect:/login?registration_success";
    }
}