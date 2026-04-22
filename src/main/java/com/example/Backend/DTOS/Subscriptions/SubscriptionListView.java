package com.example.Backend.DTOS.Subscriptions;

import java.time.LocalDate;

public class SubscriptionListView {
    private Long id;
    private String providerName;
    private String providerImage;
    private String plan;
    private  double amount;
    private String status;
    private String billing;
    private LocalDate startDate;
    private LocalDate endDate;

    public SubscriptionListView(Long id, String providerName, String providerImage, String plan, double amount, String status, LocalDate startDate, LocalDate endDate,String billings) {
        this.id = id;
        this.providerName = providerName;
        this.providerImage = providerImage;
        this.plan = plan;
        this.amount = amount;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.billing = billings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderImage() {
        return providerImage;
    }

    public void setProviderImage(String providerImage) {
        this.providerImage = providerImage;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }
}

