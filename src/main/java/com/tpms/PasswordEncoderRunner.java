package com.tpms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderRunner implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderRunner(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- PASSWORD ENCODER ---");
        System.out.println("Hashed 'staffpassword456': " + passwordEncoder.encode("staffpassword456"));
        System.out.println("Hashed 'adminpassword': " + passwordEncoder.encode("adminpassword"));
        System.out.println("Hashed 'reviewerpassword': " + passwordEncoder.encode("reviewerpassword"));
        System.out.println("------------------------");
    }
}