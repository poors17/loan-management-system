package com.loan.demo.dto;

import org.springframework.web.multipart.MultipartFile;

public class LoanApplicationDTO {

    // Personal
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

    // Loan
    private Double amount;
    private Integer term;
    private String purpose;

    // Employment
    private String employmentType;
    private Double income;
    private String organization;

    // Bank
    private String bankName;
    private String accountNumber;
    private String ifsc;
    private String accountType;

    // Files
    private MultipartFile photo;
    private MultipartFile addressProof;
    private MultipartFile incomeProof;
    
    private Long userId; // new field

 // Add getter and setter
 public Long getUserId() {
     return userId;
 }

 public void setUserId(Long userId) {
     this.userId = userId;
 }

    // ✅ Getters and Setters
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

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Integer getTerm() { return term; }
    public void setTerm(Integer term) { this.term = term; }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }

    public Double getIncome() { return income; }
    public void setIncome(Double income) { this.income = income; }

    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getIfsc() { return ifsc; }
    public void setIfsc(String ifsc) { this.ifsc = ifsc; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public MultipartFile getPhoto() { return photo; }
    public void setPhoto(MultipartFile photo) { this.photo = photo; }

    public MultipartFile getAddressProof() { return addressProof; }
    public void setAddressProof(MultipartFile addressProof) { this.addressProof = addressProof; }

    public MultipartFile getIncomeProof() { return incomeProof; }
    public void setIncomeProof(MultipartFile incomeProof) { this.incomeProof = incomeProof; }
}
