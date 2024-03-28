package com.insightgeeks.bloodbank.dto;

public class BloodRequestSummaryDTO {
    private String bloodGroup;
    private int totalCoins;
    private double commission;

    public BloodRequestSummaryDTO() {
    }

    public BloodRequestSummaryDTO(String bloodGroup, int totalCoins, double commission) {
        this.bloodGroup = bloodGroup;
        this.totalCoins = totalCoins;
        this.commission = commission;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public void setTotalCoins(int totalCoins) {
        this.totalCoins = totalCoins;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String toString()
    {
        return this.bloodGroup + "-" + this.commission + "-" + this.totalCoins;
    }
}
