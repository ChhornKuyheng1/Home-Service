package com.example.Backend.DTOS.MobileProviders.planViews;

import java.time.LocalDate;

public class BillingHistory {
    private String planName;
    private String payDate;
    private String status;
    private double total;

    public BillingHistory(String planName, String payDate, String status, double total) {
        this.planName = planName;
        this.payDate = payDate;
        this.status = status;
        this.total = total;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
