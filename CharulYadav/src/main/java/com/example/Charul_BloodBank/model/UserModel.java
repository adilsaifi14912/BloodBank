package com.example.Charul_BloodBank.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity(name = "user_table")
public class UserModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long blood_bank_id;
    @Column
    @NotEmpty
    private String username;
    @Column
    @NotEmpty
    private String password;
    @Column
    @NotEmpty
    private String role;
    @Column
    @NotNull
    private LocalDateTime createdOn;
    @Column
    @NotEmpty
    private String name;
    @Column
    @NotNull
    private Date dob;
    @Column
    @NotNull
    private String bloodGroup;
    @Column
    @NotNull
    private String createdBy;
    public void setCreatedOn(){this.createdOn=LocalDateTime.now();}
    public LocalDateTime getCreatedOn(){return createdOn;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRole(String role){
        this.role=role;
    }
    public String getRole(){return role;}

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

    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy() {
        this.createdBy = "AUTO";
    }
}

