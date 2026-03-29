package com.loan.demo.controller;

import java.util.List;

import com.loan.demo.model.LoanApplication;
import com.loan.demo.service.LoanApprovalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")  // Allow frontend from local environment
@RestController
@RequestMapping("/api/loan-approvals")
public class LoanApprovalController {

    @Autowired
    private LoanApprovalService loanApprovalService;

    private static final Logger logger = LoggerFactory.getLogger(LoanApprovalController.class);

    // Approve loan and return appropriate response
    @PutMapping("/approve/{id}")
    public ResponseEntity<Object> approveLoan(@PathVariable Long id) {
        logger.info("Request to approve loan with ID: {}", id);

        LoanApplication loan = loanApprovalService.approveLoan(id);

        if (loan != null) {
            logger.info("Loan with ID: {} approved successfully.", id);
            return ResponseEntity.ok(createResponse("Loan approved successfully", loan));
        } else {
            logger.warn("Loan with ID: {} is already approved or rejected, cannot approve again.", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(createResponse("Loan is already approved or rejected, cannot approve again.", null));
        }
    }

    // Reject loan and return appropriate response
    @PutMapping("/reject/{id}")
    public ResponseEntity<Object> rejectLoan(@PathVariable Long id) {
        logger.info("Request to reject loan with ID: {}", id);

        LoanApplication loan = loanApprovalService.rejectLoan(id);

        if (loan != null) {
            logger.info("Loan with ID: {} rejected successfully.", id);
            return ResponseEntity.ok(createResponse("Loan rejected successfully", loan));
        } else {
            logger.warn("Loan with ID: {} is already approved or rejected, cannot reject again.", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(createResponse("Loan is already approved or rejected, cannot reject again.", null));
        }
    }

    // GET endpoint to retrieve all loan applications
    @GetMapping("/all")
    public ResponseEntity<Object> getAllLoans() {
        logger.info("Request to fetch all loan applications.");

        List<LoanApplication> loans = loanApprovalService.getAllApplications();

        if (loans.isEmpty()) {
            logger.warn("No loan applications found.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No loans found");
        }

        logger.info("Returning {} loan applications.", loans.size());
        return ResponseEntity.ok(loans);  // Return list of loans with 200 OK status
    }

    // GET endpoint to retrieve only pending loan applications
    @GetMapping("/pending")
    public ResponseEntity<Object> getPendingLoans() {
        logger.info("Request to fetch pending loan applications.");

        List<LoanApplication> loans = loanApprovalService.getPendingLoans();

        if (loans.isEmpty()) {
            logger.warn("No pending loan applications found.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No pending loans found");
        }

        logger.info("Returning {} pending loan applications.", loans.size());
        return ResponseEntity.ok(loans);  // Return list of pending loans with 200 OK status
    }

    // Helper method to create a response structure for consistency
    private ResponseEntity<Object> createResponse(String message, LoanApplication loan) {
        return ResponseEntity.ok(new ResponseMessage("success", message, loan));
    }

    // Response message class to maintain consistent response format
    public static class ResponseMessage {
        private String status;
        private String message;
        private LoanApplication loan;  // Include loan details in the response

        public ResponseMessage(String status, String message, LoanApplication loan) {
            this.status = status;
            this.message = message;
            this.loan = loan;
        }

        // Getters and setters for status, message, and loan
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LoanApplication getLoan() {
            return loan;
        }

        public void setLoan(LoanApplication loan) {
            this.loan = loan;
        }
    }
}
