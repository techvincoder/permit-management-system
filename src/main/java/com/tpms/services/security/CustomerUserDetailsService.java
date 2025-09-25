package com.tpms.services.security;

import com.tpms.dao.CustomerRepository;
import com.tpms.entities.Customer;
import com.tpms.security.CustomerUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customerUserDetailsService") // Give it a unique name
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.getCustomerByUsername(email);
        if(customer ==null) {
        	throw new UsernameNotFoundException("Couldn't find user");
        }
        return new CustomerUserDetails(customer);
    }
}
