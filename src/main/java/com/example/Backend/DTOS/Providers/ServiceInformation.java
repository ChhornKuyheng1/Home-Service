package com.example.Backend.DTOS.Providers;

import java.util.List;

public class ServiceInformation {
    private List<String> service;
    private int serviceRadius;
    private List<String> skills;

    public ServiceInformation(List<String> service, int serviceRadius, List<String> skills) {
        this.service = service;
        this.serviceRadius = serviceRadius;
        this.skills = skills;
    }

    public List<String> getService() {
        return service;
    }

    public void setService(List<String> service) {
        this.service = service;
    }

    public int getServiceRadius() {
        return serviceRadius;
    }

    public void setServiceRadius(int serviceRadius) {
        this.serviceRadius = serviceRadius;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
