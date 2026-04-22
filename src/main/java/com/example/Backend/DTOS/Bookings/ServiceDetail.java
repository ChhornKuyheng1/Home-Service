package com.example.Backend.DTOS.Bookings;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceDetail {
    private String serviceName;
    private String skillName;
    private LocalDate serviceDate;
    private String serviceTime;
    private String duration;
    private String serviceAmount;

    public ServiceDetail(String serviceName, String skillName, LocalDate serviceDate, String serviceTime, String duration, String serviceAmount) {
        this.serviceName = serviceName;
        this.skillName = skillName;
        this.serviceDate = serviceDate;
        this.serviceTime = serviceTime;
        this.duration = duration;
        this.serviceAmount = serviceAmount;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }
}
