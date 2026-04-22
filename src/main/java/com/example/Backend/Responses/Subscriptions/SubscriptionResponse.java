package com.example.Backend.Responses.Subscriptions;

import java.time.LocalDate;

public class SubscriptionResponse {
    private Long id;
    private String plan;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private double total;
    private boolean status;
    private String message;
    public SubscriptionResponse(Long id,String plan, String category, LocalDate startDate, LocalDate endDate, double total,boolean status,String message) {
        this.id = id;
        this.plan = plan;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.total = total;
        this.status = status;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
