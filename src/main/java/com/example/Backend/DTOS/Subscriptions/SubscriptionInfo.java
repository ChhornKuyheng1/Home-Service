package com.example.Backend.DTOS.Subscriptions;

import java.time.LocalDate;

public class SubscriptionInfo {
    private Long id;
    private String status;
    private String plan;
    private double amount;
    private LocalDate startDate;
    private LocalDate endDate;

    public SubscriptionInfo(Long id, String status, String plan, double amount, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.status = status;
        this.plan = plan;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
