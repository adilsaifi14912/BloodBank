package com.example.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class UserSignupDto {
    @NotEmpty
    @Size(min = 5,max=10)
    private String username;
    @NotEmpty
    @Size(min = 4,max = 15)
    private String name;
    @NotEmpty
    @Size(min = 4,max = 10)
    private String password;
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
        return password;
    }
    public Date getDob(){return dob;}
}

