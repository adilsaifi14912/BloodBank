package com.insightgeeks.bloodbank.entities;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Component
@Scope("prototype")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

    @Column
   private String userName;

    @Column
   private String role;

    @Column
   private String password;

    @Column
   private LocalDate dob;

    @Column
   private long phoneNumber;

    @Column
   private String adddress;


    public UserModel() {
    }

    public UserModel(int id, String userName, String role, String password, LocalDate dob, long phoneNumber, String adddress){
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.password = password;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.adddress = adddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdddress() {
        return adddress;
    }

    public void setAdddress(String adddress) {
        this.adddress = adddress;
    }
}
