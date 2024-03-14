package com.BloodBank.dto;

import java.sql.Date;

public class UserSignUpDTO {
    private String username;
    private String password;
    private String name;
    private Long phone;
    private Date dob;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null && !username.trim().isEmpty()) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && !password.trim().isEmpty()) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        if (phone != null && phone > 0) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Phone number cannot be null or negative");
        }
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        if (dob != null) {
            this.dob = dob;
        } else {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
    }
}
