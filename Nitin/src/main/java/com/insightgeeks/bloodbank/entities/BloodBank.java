package com.insightgeeks.bloodbank.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Component
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String bloodGroup;

    @Column
    private String type;

    @Column
    private int quantity;

    @Column
    private LocalDateTime createdOn;

    @Column
    private LocalDateTime updatedOn;

    @Column
    private String createdBy;

    @Column
    private String updatedBy;

    @Column
    private String status;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @Column
    private String remark;

    @Column
    private int coins;


    public BloodBank() {
    }

    public BloodBank(int id, String bloodGroup, String type, int quantity,
                     LocalDateTime createdOn, LocalDateTime updatedOn, String createdBy,
                     String updatedBy, String status, UserModel userModel, String remark,
                     int coins) {
        this.id = id;
        this.bloodGroup = bloodGroup;
        this.type = type;
        this.quantity = quantity;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.status = status;
        this.userModel = userModel;
        this.remark = remark;
        this.coins = coins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

}
