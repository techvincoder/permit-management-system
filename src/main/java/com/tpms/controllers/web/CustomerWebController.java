package com.tpms.controllers.web;

import com.tpms.dto.CustomerDto;
import com.tpms.entities.Customer;
import com.tpms.services.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder; // Add this import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// We remove the class-level @RequestMapping to handle multiple base paths
@Controller
public class CustomerWebController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder; // ADDED: for hashing passwords

    // UPDATED: The constructor now needs the PasswordEncoder
    public CustomerWebController(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    // --- Staff-Facing Methods (Protected by Security) ---

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

    // --- Public-Facing Methods (Will be permitted by Security) ---

    // NEW METHOD: Shows the public registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/register";
    }

    // NEW METHOD: Processes the public registration form
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("customer") Customer customer) {
        // Securely hash the password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setIsActive(true); // Set new users to active by default

        customerService.addCustomer(customer);

        // Redirect to the login page with a success message
        return "redirect:/login?registration_success";
    }
}