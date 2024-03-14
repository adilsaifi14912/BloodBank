package com.example.Charul_BloodBank.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

public class UserSignUpDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String name;

    private String password;
    @NotEmpty
    private String bloodGroup;
    @NotNull
    private Date dob;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
        if(password.isEmpty())
        {
            password=String.valueOf(dob);
        }
        return password;
    }
    public Date getDob(){return dob;}

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
}