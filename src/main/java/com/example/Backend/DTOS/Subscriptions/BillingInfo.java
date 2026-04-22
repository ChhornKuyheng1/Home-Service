package com.example.Backend.DTOS.Subscriptions;

public class BillingInfo {
    private double planPrice;
    private String cycle;

    public BillingInfo(double planPrice, String cycle) {
        this.planPrice = planPrice;
        this.cycle = cycle;
    }

    public double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(double planPrice) {
        this.planPrice = planPrice;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
}
