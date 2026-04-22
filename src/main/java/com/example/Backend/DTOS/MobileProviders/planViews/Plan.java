package com.example.Backend.DTOS.MobileProviders.planViews;

import java.time.LocalDate;

public class Plan {
    private String planName;
    private String category;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private String image;

    public Plan(String planName, String category, String status, LocalDate startDate, LocalDate endDate, double price, String image) {
        this.planName = planName;
        this.category = category;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.image = image;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
