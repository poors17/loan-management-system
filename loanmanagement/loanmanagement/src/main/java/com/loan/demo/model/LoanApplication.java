package com.loan.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "loan_applications")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private Integer age;
    private String dob;
    private String nationality;
    private String maritalStatus;
    private String panCard;
    private String aadharCard;
    private String contactNo;
    private String email;
    private String currentAddress;
    private String permanentAddress;
    private Double loanAmount;
    private Integer loanTerm;
    private String purpose;
    private String employmentType;
    private Double annualIncome;
    private String employerOrBusiness;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String accountType;
    private String status;  // "PENDING", "APPROVED", "REJECTED"

    // EMI and Interest Rate fields for loan approval and calculation
    private Double emi;
    private Double interestRate;

    // LOB Fields with Lazy Loading (photo, address proof, and income proof)
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private byte[] photo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private byte[] addressProof;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private byte[] incomeProof;

    // Relationship to User (LoanApplication -> User)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign key relationship with User entity
    private User user;

    // ===== Getters and Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }

    public String getPanCard() { return panCard; }
    public void setPanCard(String panCard) { this.panCard = panCard; }

    public String getAadharCard() { return aadharCard; }
    public void setAadharCard(String aadharCard) { this.aadharCard = aadharCard; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCurrentAddress() { return currentAddress; }
    public void setCurrentAddress(String currentAddress) { this.currentAddress = currentAddress; }

    public String getPermanentAddress() { return permanentAddress; }
    public void setPermanentAddress(String permanentAddress) { this.permanentAddress = permanentAddress; }

    public Double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(Double loanAmount) { this.loanAmount = loanAmount; }

    public Integer getLoanTerm() { return loanTerm; }
    public void setLoanTerm(Integer loanTerm) { this.loanTerm = loanTerm; }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }

    public Double getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(Double annualIncome) { this.annualIncome = annualIncome; }

    public String getEmployerOrBusiness() { return employerOrBusiness; }
    public void setEmployerOrBusiness(String employerOrBusiness) { this.employerOrBusiness = employerOrBusiness; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Added for EMI and Interest Rate
    public Double getEmi() { return emi; }
    public void setEmi(Double emi) { this.emi = emi; }

    public Double getInterestRate() { return interestRate; }
    public void setInterestRate(Double interestRate) { this.interestRate = interestRate; }

    // Getters and Setters for LOB Fields (photo, address proof, income proof)
    @JsonIgnore
    public byte[] getPhoto() { return photo; }
    public void setPhoto(byte[] photo) { this.photo = photo; }

    @JsonIgnore
    public byte[] getAddressProof() { return addressProof; }
    public void setAddressProof(byte[] addressProof) { this.addressProof = addressProof; }

    @JsonIgnore
    public byte[] getIncomeProof() { return incomeProof; }
    public void setIncomeProof(byte[] incomeProof) { this.incomeProof = incomeProof; }

    // ===== Convenience Method to Get User's Username =====
    @Transient
    public String getUsername() {
        return user != null ? user.getUsername() : null;
    }

    // Set User (not to be exposed directly)
    public void setUser(User user) { 
        this.user = user; 
    }
}
