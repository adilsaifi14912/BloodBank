package com.insightgeeks.bloodbank.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Component
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private int id;

    @Column
   private String username;

    @Column
   private String role;

    @Column
   private String password;

    @Column
   private LocalDate dateOfBirth;

    @Column
   private long phoneNumber;

    @Column
   private String adddress;

    @Column
    private String bloodGroup;

    @Column
    private LocalDate createdOn;

    @Column
    private String createdBy;

    @Column
    private int parentId;

    @Column
    private String blockStatus;


    public UserModel() {
    }

    public UserModel(int id, String username, String role, String password, LocalDate dateOfBirth,
                     long phoneNumber, String adddress, String bloodGroup, LocalDate createdOn, String createdBy,
                     int parentId, String blockStatus) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.adddress = adddress;
        this.bloodGroup = bloodGroup;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.parentId = parentId;
        this.blockStatus = blockStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String userName) {
        this.username = userName;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return adddress;
    }

    public void setAdddress(String adddress) {
        this.adddress = adddress;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(String blockStatus) {
        this.blockStatus = blockStatus;
    }

    public String toString()
    {
        return "username-"+username + " | " + "dob-"+dateOfBirth + " | "
                + "phoneNumber-"+phoneNumber + " | " + "address-"+adddress;
    }


}
