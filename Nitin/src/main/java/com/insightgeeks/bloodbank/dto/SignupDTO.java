package com.insightgeeks.bloodbank.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Component
@Scope("prototype")
public class SignupDTO {

    @NotEmpty(message = "username cannot be empty or null")
    private String username;

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

    @NotEmpty
    private String bloodGroup;

    @NotNull
    private float commision;

    private int id;
    private String role;
    private LocalDate createdOn;
    private String createdBy;
    private String blockStatus;
    private int coins;
    private LocalDate nextRequestEligibleDate;

    public SignupDTO() {
    }

    public SignupDTO(String username, String password, String confirmPassword, String dateOfBirth,
                     long phoneNumber, String address, String bloodGroup, float commision, int id,
                     String role, LocalDate createdOn, String createdBy, String blockStatus, int coins,
                     LocalDate nextRequestEligibleDate) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.commision = commision;
        this.id = id;
        this.role = role;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.blockStatus = blockStatus;
        this.coins = coins;
        this.nextRequestEligibleDate = nextRequestEligibleDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public float getCommision() {
        return commision;
    }

    public void setCommision(float commision) {
        this.commision = commision;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getCoins() { return coins; }

    public void setCoins(int coins) { this.coins = coins; }

    public LocalDate getNextRequestEligibleDate() {
        return nextRequestEligibleDate;
    }

    public void setNextRequestEligibleDate(LocalDate nextRequestEligibleDate) {
        this.nextRequestEligibleDate = nextRequestEligibleDate;
    }

    public String toString()
    {
        return getUsername();
    }
}
