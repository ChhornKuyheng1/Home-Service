package com.example.Backend.DTOS.MobileProviders.ProviderViewDetails.Services;

import java.util.List;

public class ServiceDetail {
    private String serviceName;
    private List<SkillDetail> skills;

    public ServiceDetail(String serviceName, List<SkillDetail> skills) {
        this.serviceName = serviceName;
        this.skills = skills;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<SkillDetail> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDetail> skills) {
        this.skills = skills;
    }
}
