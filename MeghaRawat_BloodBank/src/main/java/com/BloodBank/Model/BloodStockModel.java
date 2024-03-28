package com.BloodBank.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class BloodStockModel {
    @Id
    private String bloodGroup;
    private LocalDateTime lastUpdate;
    private int coinsPerUnit;
    private int quantity;
    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getCoinsPerUnit() {
        return coinsPerUnit;
    }

    public void setCoinsPerUnit(int coinsPerUnit) {
        this.coinsPerUnit = coinsPerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
