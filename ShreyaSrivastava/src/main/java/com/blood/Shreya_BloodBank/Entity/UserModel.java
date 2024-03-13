package com.blood.Shreya_BloodBank.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserModel")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // or other appropriate strategy
//    @Column(name = "id")

    private Long id;
    private LocalDateTime createdOn;
    private String createdby;
    private String username;
    private Date dob;
    private String phone;
    private String address;
    private String password;
    private String bloodGroup;
    private String role;
    private String choice;
//    private boolean firstLogin = true;
//    private String currentPassword;
//    private String newPassword;

    public LocalDateTime getCreatedOn() {
        //System.out.println("user model");
        return createdOn;
    }
    public void setCreatedOn(LocalDateTime date) {
        this.createdOn = date;
    }
    public String getCreatedby() {
        return createdby;
    }
    public void setCreatedby(String by) {
        this.createdby = by;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        //System.out.println("in service"+username);
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
        //System.out.println(contactNumber);
        this.phone = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        //System.out.println(password);
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

//    public boolean isFirstLogin() {
//        return firstLogin;
//    }
//
//    public void setFirstLogin(boolean firstLogin) {
//        this.firstLogin = firstLogin;
//    }
//
//    public String getCurrentPassword() {
//        return currentPassword;
//    }
//
//    public void setCurrentPassword(String currentPassword) {
//        this.currentPassword = currentPassword;
//    }
}
