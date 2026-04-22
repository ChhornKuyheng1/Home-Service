package com.example.Backend.DTOS.MobileProviders.Profiles;

import java.util.List;

public class Profile {
    private String businessName;
    private String image;
    private String planName;
    private List<String> topSkill;

    public Profile(String businessName, String image, String planName, List<String> topSkill) {
        this.businessName = businessName;
        this.image = image;
        this.planName = planName;
        this.topSkill = topSkill;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public List<String> getTopSkill() {
        return topSkill;
    }

    public void setTopSkill(List<String> topSkill) {
        this.topSkill = topSkill;
    }
}
