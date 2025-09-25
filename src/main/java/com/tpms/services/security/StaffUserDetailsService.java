package com.tpms.services.security;

import com.tpms.dao.StaffAccountsRepository;
import com.tpms.entities.StaffAccounts;
import com.tpms.security.StaffAccountsUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("staffUserDetailsService") // Give it a unique name
public class StaffUserDetailsService implements UserDetailsService {

    private final StaffAccountsRepository staffAccountsRepository;

    public StaffUserDetailsService(StaffAccountsRepository staffAccountsRepository) {
        this.staffAccountsRepository = staffAccountsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        StaffAccounts staffAccount = staffAccountsRepository.getStaffByUsername(email);
        
        if(staffAccount == null) {
        	throw new UsernameNotFoundException("Couldn't find user");
        }
        return new StaffAccountsUserDetails(staffAccount);
    }
}
