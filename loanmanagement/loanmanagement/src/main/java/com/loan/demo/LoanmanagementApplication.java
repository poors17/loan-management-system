package com.loan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import com.loan.demo.model.User;
import com.loan.demo.repository.UserRepository;

@SpringBootApplication
public class LoanmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanmanagementApplication.class, args);
    }

    // ✅ Inject repository
    @Autowired
    private UserRepository userRepository;

    // ✅ Create admin user automatically
    @PostConstruct
    public void initAdmin() {
        if (userRepository.findByUsername("admin1").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin1");
            admin.setPassword("admin123"); // ✅ plain password
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }
}