package com.insightgeeks.bloodbank.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SignupDTO {

    @NotEmpty(message = "username cannot be empty or null")
    private String username;

    @NotNull(message = "parentId cannot be empty or null")
    private  int parentId;

    @NotEmpty(message = "user entered password cannot be empty or null")
    private String setpassword;

    @NotEmpty(message = "user re-entered password cannot be empty or null")
    private String recheckpassword;

    @NotEmpty(message = "user date or birth cannot be empty or null")
    private String dateOfBirth;

    @NotNull(message = "user cannot be null")
    private long phoneNumber;

    @NotEmpty(message = "user address cannot be empty or null")
    private String address;


    public SignupDTO() {
    }

    public SignupDTO(String username, int parentId, String setpassword, String recheckpassword, String dateOfBirth, long phoneNumber, String address) {
        this.username = username;
        this.parentId = parentId;
        this.setpassword = setpassword;
        this.recheckpassword = recheckpassword;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public String getSetpassword() {
        return setpassword;
    }

    public void setSetpassword(String setpassword) {
        this.setpassword = setpassword;
    }

    public String getRecheckpassword() {
        return recheckpassword;
    }

    public void setRecheckpassword(String recheckpassword) {
        this.recheckpassword = recheckpassword;
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
}
