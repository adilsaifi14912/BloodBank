package com.BloodBank.dto;

import java.sql.Date;

public class UserSignUpDTO {
    private String username;
    private String password;
    private String name;
    private Long phone;
    private Date dob;
    private String address;
    private String bloodGroup;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
            this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
