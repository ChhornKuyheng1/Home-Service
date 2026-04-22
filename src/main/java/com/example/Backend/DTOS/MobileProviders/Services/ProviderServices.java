package com.example.Backend.DTOS.MobileProviders.Services;

import java.util.List;

public class ProviderServices {
    private Long activeService;
    private Long activeSkill;
    private List<ProviderJob> service;

    public ProviderServices(Long activeService, Long activeSkill, List<ProviderJob> service) {
        this.activeService = activeService;
        this.activeSkill = activeSkill;
        this.service = service;
    }

    public Long getActiveService() {
        return activeService;
    }

    public void setActiveService(Long activeService) {
        this.activeService = activeService;
    }

    public Long getActiveSkill() {
        return activeSkill;
    }

    public void setActiveSkill(Long activeSkill) {
        this.activeSkill = activeSkill;
    }

    public List<ProviderJob> getService() {
        return service;
    }

    public void setService(List<ProviderJob> service) {
        this.service = service;
    }
}
