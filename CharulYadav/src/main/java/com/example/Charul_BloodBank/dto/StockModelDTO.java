package com.example.Charul_BloodBank.dto;

import java.time.LocalDateTime;

public class StockModelDTO {
    private String bloodGroup;
    private int coinPerUnit;
    private int units;
    private LocalDateTime lastUpdate;

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getCoinPerUnit() {
        return coinPerUnit;
    }

    public void setCoinPerUnit(int coinPerUnit) {
        this.coinPerUnit = coinPerUnit;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
