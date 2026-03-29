package com.loan.demo.controller;

import com.loan.demo.dto.LoanApplicationDTO;
import com.loan.demo.model.LoanApplication;
import com.loan.demo.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@RequestMapping("/api/loan")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanService;

    @PostMapping("/apply")
    public LoanApplication applyLoan(@ModelAttribute LoanApplicationDTO dto) throws IOException {
        LoanApplication loan = new LoanApplication();

        // Personal
        loan.setFullName(dto.getFullName());
        loan.setAge(dto.getAge());
        loan.setDob(dto.getDob());
        loan.setNationality(dto.getNationality());
        loan.setMaritalStatus(dto.getMaritalStatus());
        loan.setPanCard(dto.getPanCard());
        loan.setAadharCard(dto.getAadharCard());
        loan.setContactNo(dto.getContactNo());
        loan.setEmail(dto.getEmail());
        loan.setCurrentAddress(dto.getCurrentAddress());
        loan.setPermanentAddress(dto.getPermanentAddress());

        // Loan details
        loan.setLoanAmount(dto.getAmount());
        loan.setLoanTerm(dto.getTerm());
        loan.setPurpose(dto.getPurpose());

        // Employment
        loan.setEmploymentType(dto.getEmploymentType());
        loan.setAnnualIncome(dto.getIncome());
        loan.setEmployerOrBusiness(dto.getOrganization());

        // Bank details
        loan.setBankName(dto.getBankName());
        loan.setAccountNumber(dto.getAccountNumber());
        loan.setIfscCode(dto.getIfsc());
        loan.setAccountType(dto.getAccountType());

        // Files
        MultipartFile photo = dto.getPhoto();
        MultipartFile addressProof = dto.getAddressProof();
        MultipartFile incomeProof = dto.getIncomeProof();

        if (photo != null && !photo.isEmpty()) {
            loan.setPhoto(photo.getBytes());
        }
        if (addressProof != null && !addressProof.isEmpty()) {
            loan.setAddressProof(addressProof.getBytes());
        }
        if (incomeProof != null && !incomeProof.isEmpty()) {
            loan.setIncomeProof(incomeProof.getBytes());
        }

        loan.setStatus("PENDING"); // Default status

        // Save with user ID
        return loanService.submitLoanApplication(loan, dto.getUserId());
    }

    @GetMapping("/all")
    public List<LoanApplication> getAllApplications() {
        return loanService.getAllApplications();
    }

    @GetMapping("/user/{userId}")
    public List<LoanApplication> getUserApplications(@PathVariable Long userId) {
        return loanService.getApplicationsByUserId(userId);
    }

    // ✅ View Address Proof as PDF
    @GetMapping("/{id}/address-proof")
    public ResponseEntity<byte[]> getAddressProof(@PathVariable Long id) {
        Optional<LoanApplication> loanOpt = loanService.getApplicationById(id);
        if (loanOpt.isPresent() && loanOpt.get().getAddressProof() != null) {
            byte[] pdf = loanOpt.get().getAddressProof();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename("address-proof.pdf").build());

            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ View Income Proof as PDF
    @GetMapping("/{id}/income-proof")
    public ResponseEntity<byte[]> getIncomeProof(@PathVariable Long id) {
        Optional<LoanApplication> loanOpt = loanService.getApplicationById(id);
        if (loanOpt.isPresent() && loanOpt.get().getIncomeProof() != null) {
            byte[] pdf = loanOpt.get().getIncomeProof();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename("income-proof.pdf").build());

            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

}
