package com.example.Dto;

import com.example.Model.UserModel;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserSignupDto {
    @NotEmpty  //prevent from null and empty
//    @Size(min = 5,max=10)
    private String username;
    @NotEmpty
//    @Size(min = 4,max = 15)
    private String name;
    @NotNull
    private Date dob;
    @NotEmpty
    private String bloodGroup;

    private String password;
//    @NotEmpty
    private String role;
//    @NotEmpty
    private String createdBy;
    private LocalDateTime createdOn;
    private boolean isFirstLogin=true;
    private long loginAttempt=0;
    private boolean lockStatus=false;

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }

    public long getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(long loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    public boolean isLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(boolean lockStatus) {
        this.lockStatus = lockStatus;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public LocalDateTime getCreatedOn() {return createdOn;}

    public void setCreatedOn(LocalDateTime createdOn) {this.createdOn = createdOn;}

    public String getCreatedBy() {return createdBy;}

    public void setCreatedBy(String createdBy) {this.createdBy = createdBy;}

    public String getBloodGroup() {return bloodGroup;}

    public void setBloodGroup(String bloodGroup) {this.bloodGroup = bloodGroup;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setDob(Date dob){this.dob=dob;}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    public Date getDob(){return dob;}
}

