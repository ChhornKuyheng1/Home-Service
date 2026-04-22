package com.example.Backend.Requests;

import java.util.List;

public class PersonalRequest {
    private Long providerId;
    private String businessName;
    private String description;
    private int serviceRadius;
    private List<Long> topSkill;

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServiceRadius() {
        return serviceRadius;
    }

    public void setServiceRadius(int serviceRadius) {
        this.serviceRadius = serviceRadius;
    }

    public List<Long> getTopSkill() {
        return topSkill;
    }

    public void setTopSkill(List<Long> topSkill) {
        this.topSkill = topSkill;
    }
}
