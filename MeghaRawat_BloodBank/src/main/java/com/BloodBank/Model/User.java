package com.BloodBank.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private Long phone;
    private LocalDate dob;
    private Timestamp created_date_time;
    private  String createdBy;
    private String modifyBy;sudo 
    private  String role;
    private Timestamp updated_date_time;
    public void setUpdated_date_time(Timestamp updated_date_time) {
        this.updated_date_time = updated_date_time;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreated_date_time(Timestamp created_date_time) {
        this.created_date_time = created_date_time;
    }

    public Timestamp getUpdated_date_time() {
        return updated_date_time;
    }

    public String getRole() {
        return role;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getCreated_date_time() {
        return created_date_time;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Long getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }


}

