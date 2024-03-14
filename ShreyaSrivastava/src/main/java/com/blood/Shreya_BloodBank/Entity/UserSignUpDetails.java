package com.blood.Shreya_BloodBank.Entity;


import java.sql.Date;

public class UserSignUpDetails {
    private String username;
    private Date dob;
    private String phone;
    private String address;
    private String password;
    private String bloodGroup;
    private String role;
    private String choice;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
       // System.out.println(username);
        this.username = username;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String contactNumber) {
        this.phone = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        if(password.isEmpty())
            password=String.valueOf(dob);
        return password;
    }

    public void setPassword(String password) {
        //System.out.println("in dto"+password);
        this.password = password;
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

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
