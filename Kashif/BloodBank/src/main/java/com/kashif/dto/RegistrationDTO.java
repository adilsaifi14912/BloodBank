package com.kashif.dto;


import java.util.Date;

public class RegistrationDTO {

    private String email;
    private String name;
    private String password;
    private String dob;
    private String username;
    private String role;
    private String address;
    private String bloodGroup;  //added extra on 11 Mar 2024
    private Long commission;   //added extra fields on 14 Mar 2024
    private Date creationTime;
    private String createdBy;
    private String modifyBy;
    private Date updatedTime;
    private boolean newUser;
    private boolean blockedStatus;

    public boolean isBlockedStatus() {
        return blockedStatus;
    }

    public void setBlockedStatus(boolean blockedStatus) {
        this.blockedStatus = blockedStatus;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "email='" + email + '\'' + ", name='" + name + '\'' + ", password='" + password + '\'' + ", dob='" + dob + '\'' + ", username='" + username + '\'' + ", role='" + role + '\'' + ", address='" + address + '\'' + '}';
    }
}
