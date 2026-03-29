package com.loan.demo.service;

import com.loan.demo.model.LoanApplication;
import com.loan.demo.repository.LoanApplicationRepository;
import com.loan.demo.model.User;
import com.loan.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanRepo;

    @Autowired
    private UserRepository userRepo;

    // Method to submit loan application with user association
    public LoanApplication submitLoanApplication(LoanApplication loan, Long userId) throws IOException {
        Optional<User> userOpt = userRepo.findById(userId);

        // Check if the user exists and set the user on the loan application
        if (userOpt.isPresent()) {
            loan.setUser(userOpt.get());
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        // Save the loan application to the database
        return loanRepo.save(loan);
    }

    // Method to retrieve all loan applications
    public List<LoanApplication> getAllApplications() {
        return loanRepo.findAll();
    }

    // Method to fetch loan applications by a specific user ID
    public List<LoanApplication> getApplicationsByUserId(Long userId) {
        return loanRepo.findByUserId(userId);  // This method should be defined in the LoanApplicationRepository
    }

    // Method to fetch a loan application by its ID
    public Optional<LoanApplication> getApplicationById(Long id) {
        return loanRepo.findById(id);  // Fetch the loan application by ID
    }

    // Additional logic for handling the approval or rejection of loans can be added here if needed

}
