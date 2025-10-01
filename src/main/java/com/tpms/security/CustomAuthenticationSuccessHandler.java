package com.tpms.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            
            // If the user has an ADMIN or REVIEWER role, send them to the staff dashboard.
            if (role.equals("ROLE_ADMIN") || role.equals("ROLE_REVIEWER")) {
                response.sendRedirect("/staff/dashboard");
                return; // Stop processing
            }
        }

        // If the user is not a staff member, they must be a customer. Send them to their dashboard.
        response.sendRedirect("/my-applications");
    }
}