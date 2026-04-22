package com.example.Backend.DTOS.MobileProviders.Jobs;

import java.time.LocalTime;

public class JobBookingView {
    private String skillName;
    private String price;
    private String serviceName;
    private String image;
    private String duration;

    public JobBookingView(String skillName, String price, String serviceName, String image, String duration) {
        this.skillName = skillName;
        this.price = price;
        this.serviceName = serviceName;
        this.image = image;
        this.duration = duration;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
