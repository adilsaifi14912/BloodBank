package com.insightgeeks.bloodbank.dto;

import java.time.LocalDateTime;

public class BloodStockDTO {
    private String bloodGroup;
    private int units;
    private LocalDateTime lastUpdated;

    public BloodStockDTO() {
    }

    public BloodStockDTO(String bloodGroup, int units, LocalDateTime lastUpdated) {
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
