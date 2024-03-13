package com.insightgeeks.bloodbank.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SignupDTO {

    @NotEmpty(message = "username cannot be empty or null")
    private String username;

    @NotNull(message = "parentId cannot be empty or null")
    private  int parentId;

    @NotEmpty(message = "user entered password cannot be empty or null")
    private String password;

    @NotEmpty(message = "user re-entered password cannot be empty or null")
    private String confirmPassword;

    @NotEmpty(message = "user date or birth cannot be empty or null")
    private String dateOfBirth;

    @NotNull(message = "user cannot be null")
    private long phoneNumber;

    @NotEmpty(message = "user address cannot be empty or null")
    private String address;

    @NotEmpty(message = "please choose a bloodGroup")
    private String bloodGroup;

    private int id;
    private String role;
    private LocalDate createdOn;
    private String createdBy;
    private String blockStatus;



    public SignupDTO() {
    }

    public SignupDTO(String username, int parentId, String password, String confirmPassword,
                     String dateOfBirth, long phoneNumber, String address, String bloodGroup){
        this.username = username;
        this.parentId = parentId;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String  getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getBlockStatus() {
        return blockStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public void setBlockStatus(String blockStatus) {
        this.blockStatus = blockStatus;
    }
}
