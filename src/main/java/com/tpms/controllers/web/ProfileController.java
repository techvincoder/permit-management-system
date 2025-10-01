package com.tpms.controllers.web;

import com.tpms.entities.Customer;
import com.tpms.entities.StaffAccounts;
import com.tpms.services.CustomerService;
import com.tpms.services.StaffAccountsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private final StaffAccountsService staffAccountsService;
    private final CustomerService customerService;

    public ProfileController(StaffAccountsService staffAccountsService, CustomerService customerService) {
        this.staffAccountsService = staffAccountsService;
        this.customerService = customerService;
    }

    @GetMapping("/my-profile")
    public String showProfilePage(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        // Check if the user has a staff role
        boolean isStaff = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ||
                          userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_REVIEWER"));

        if (isStaff) {
            // If they are staff, fetch the StaffAccounts entity
            StaffAccounts staffAccount = staffAccountsService.findStaffAccountEntityByEmail(username);
            model.addAttribute("profile", staffAccount);
            return "staff/my-profile"; // and show the staff profile page
        } else {
            // Otherwise, they must be a customer
            Customer customer = customerService.findCustomerEntityByEmail(username);
            model.addAttribute("profile", customer);
            return "customer/my-profile"; // and show the customer profile page
        }
    }
    
    // This method saves updates from the STAFF profile form
    @PostMapping("/my-profile/staff/save")
    public String saveStaffProfile(@ModelAttribute("profile") StaffAccounts staffAccount) {
        staffAccountsService.updateStaffAccount(staffAccount); // We will create this service method
        return "redirect:/my-profile?success";
    }

    // This method saves updates from the CUSTOMER profile form
    @PostMapping("/my-profile/customer/save")
    public String saveCustomerProfile(@ModelAttribute("profile") Customer customer) {
        customerService.updateCustomer(customer); // We will create this service method
        return "redirect:/my-profile?success";
    }
}