package com.loan.demo.service;

import com.loan.demo.model.LoanApplication;
import com.loan.demo.repository.LoanApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LoanApprovalService {

    @Autowired
    private LoanApplicationRepository repository;

    private static final double FIXED_INTEREST_RATE = 0.105;  // Fixed 10.5% annual interest rate
    private static final Logger logger = LoggerFactory.getLogger(LoanApprovalService.class);

    // Approve loan and calculate EMI and interest rate
    @Transactional
    public LoanApplication approveLoan(Long id) {
        Optional<LoanApplication> optional = repository.findById(id);

        if (optional.isPresent()) {
            LoanApplication loan = optional.get();

            // Fill missing details before approving the loan
            loan = fillMissingDetails(loan);

            // Check if the loan is already approved or rejected
            if ("APPROVED".equals(loan.getStatus()) || "REJECTED".equals(loan.getStatus())) {
                logger.warn("Loan with ID: {} is already approved or rejected, cannot approve again.", id);
                return null;  // Return null if the loan is already approved or rejected
            }

            // Calculate EMI for the loan
            double emi = calculateEMI(loan.getLoanAmount(), loan.getLoanTerm(), FIXED_INTEREST_RATE);
            loan.setStatus("APPROVED");  // Set loan status to "APPROVED"
            loan.setInterestRate(FIXED_INTEREST_RATE * 100);  // Save the interest rate as percentage (10.5%)
            loan.setEmi(emi);  // Set the EMI for the loan

            logger.info("Loan with ID: {} approved and EMI calculated: {}", id, emi);
            return repository.save(loan);  // Save the updated loan application to the repository
        } else {
            logger.error("Loan with ID: {} not found.", id);
            return null;  // Return null if the loan was not found
        }
    }

    // Reject loan and set status to REJECTED
    @Transactional
    public LoanApplication rejectLoan(Long id) {
        Optional<LoanApplication> optional = repository.findById(id);

        if (optional.isPresent()) {
            LoanApplication loan = optional.get();

            // Fill missing details before rejecting the loan
            loan = fillMissingDetails(loan);

            // Check if the loan is already approved or rejected
            if ("APPROVED".equals(loan.getStatus()) || "REJECTED".equals(loan.getStatus())) {
                logger.warn("Loan with ID: {} is already approved or rejected, cannot reject again.", id);
                return null;  // Return null if the loan is already approved or rejected
            }

            // Set the status to "REJECTED"
            loan.setStatus("REJECTED");

            logger.info("Loan with ID: {} rejected.", id);
            return repository.save(loan);  // Save the updated loan application to the repository
        } else {
            logger.error("Loan with ID: {} not found.", id);
            return null;  // Return null if the loan was not found
        }
    }

  
 // Method to fill in missing details for the loan application
    private LoanApplication fillMissingDetails(LoanApplication loan) {
        // If Address Proof is missing or marked as "N/A", set it to "Submitted"
    	// If Address Proof is missing or marked as "N/A", set it to "Submitted"
    	if (loan.getAddressProof() == null || loan.getAddressProof().equals("N/A")) {
    	    loan.setAddressProof("Submitted".getBytes());  // Convert to byte[]
    	}

    	// If Income is missing or marked as "N/A", set it to "Not Provided"
    	if (loan.getAnnualIncome() == null || loan.getAnnualIncome().equals("N/A")) {
    	    loan.setAnnualIncome(0.0);  // Set to 0 or another default value
    	}


        return loan;  // Return the loan with updated details
    }


    // Method to calculate EMI based on loan amount, term, and interest rate
    private double calculateEMI(double loanAmount, int termInMonths, double interestRate) {
        if (termInMonths <= 0) {
            throw new IllegalArgumentException("Loan term must be greater than zero.");
        }

        // Convert annual interest rate to monthly rate
        double monthlyRate = interestRate / 12;

        // EMI calculation formula
        double emi = (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
        return emi;  // Return the calculated EMI value
    }

    // New method to fetch all loan applications
    public List<LoanApplication> getAllApplications() {
        return repository.findAll();  // Retrieve all loan applications from the database
    }

    // New method to fetch pending loan applications
    public List<LoanApplication> getPendingLoans() {
        return repository.findByStatus("PENDING");  // Assuming the repository has a method like this to fetch pending loans
    }

    // Method to retrieve a loan by ID (this method is used in the controller)
    public LoanApplication getLoanById(Long id) {
        return repository.findById(id).orElse(null);  // Return the loan if found, otherwise null
    }

    // Method to save a loan application (this method is used after modifying the loan details)
    public LoanApplication saveLoan(LoanApplication loan) {
        return repository.save(loan);  // Save the updated loan application to the repository
    }
}
