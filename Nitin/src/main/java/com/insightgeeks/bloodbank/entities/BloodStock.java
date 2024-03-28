package com.insightgeeks.bloodbank.entities;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class BloodStock {



    @Id
    private String bloodGroup;

    @Column
    private int units;

    @Column
    private LocalDateTime lastUpdated;

    public BloodStock() {
    }

    public BloodStock(String bloodGroup, int units, LocalDateTime lastUpdated) {
        this.bloodGroup = bloodGroup;
        this.units = units;
        this.lastUpdated = lastUpdated;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
