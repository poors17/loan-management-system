package com.loan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ✅ ADD THESE IMPORTS
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.loan.demo.model.User;
import com.loan.demo.repository.UserRepository;

@SpringBootApplication
public class LoanmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanmanagementApplication.class, args);
    }

    // ✅ ADD THESE
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ ADD THIS METHOD
    @PostConstruct
    public void initAdmin() {
        if (userRepository.findByUsername("admin1").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin1");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }
}