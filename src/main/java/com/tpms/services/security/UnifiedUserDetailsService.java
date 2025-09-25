package com.tpms.services.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary // Tells Spring to use this UserDetailsService by default
public class UnifiedUserDetailsService implements UserDetailsService {

    private final UserDetailsService staffUserDetailsService;
    private final UserDetailsService customerUserDetailsService;

    // We use @Qualifier to tell Spring exactly which bean to inject
    public UnifiedUserDetailsService(@Qualifier("staffUserDetailsService") UserDetailsService staffUserDetailsService,
                                     @Qualifier("customerUserDetailsService") UserDetailsService customerUserDetailsService) {
        this.staffUserDetailsService = staffUserDetailsService;
        this.customerUserDetailsService = customerUserDetailsService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // First, try to load the user as a staff member
            return staffUserDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            // If not found, try to load the user as a customer
            try {
                return customerUserDetailsService.loadUserByUsername(username);
            } catch (UsernameNotFoundException ex) {
                // If not found in either, then the user truly does not exist
                throw new UsernameNotFoundException("User not found with email: " + username);
            }
        }
    }
}