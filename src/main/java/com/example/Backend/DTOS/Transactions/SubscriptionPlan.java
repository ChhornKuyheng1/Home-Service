package com.example.Backend.DTOS.Transactions;

import java.time.LocalDate;

public class SubscriptionPlan {
    private String name;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private int maxService;
    private int maxSkill;

    public SubscriptionPlan(String name, String category, LocalDate startDate, LocalDate endDate, int maxService, int maxSkill) {
        this.name = name;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxService = maxService;
        this.maxSkill = maxSkill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getMaxService() {
        return maxService;
    }

    public void setMaxService(int maxService) {
        this.maxService = maxService;
    }

    public int getMaxSkill() {
        return maxSkill;
    }

    public void setMaxSkill(int maxSkill) {
        this.maxSkill = maxSkill;
    }
}
