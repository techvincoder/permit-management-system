package com.tpms.security;

import com.tpms.entities.StaffAccounts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Adapts our StaffAccounts entity to the UserDetails interface for Spring Security.
 */
public class StaffAccountsUserDetails implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5262693201692573083L;
	private final StaffAccounts staffAccount;

    public StaffAccountsUserDetails(StaffAccounts staffAccount) {
        this.staffAccount = staffAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Converts the Set<Role> from our entity into a list of SimpleGrantedAuthority
        return staffAccount.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return staffAccount.getPassword();
    }

    @Override
    public String getUsername() {
        return staffAccount.getEmail();
    }

//    @Override
//    public boolean isAccountNonExpired() { return true; }
//
//    @Override
//    public boolean isAccountNonLocked() { return true; }
//
//    @Override
//    public boolean isCredentialsNonExpired() { return true; }
//
//    @Override
//    public boolean isEnabled() {
//        return staffAccount.getIsActive();
//    }
}
