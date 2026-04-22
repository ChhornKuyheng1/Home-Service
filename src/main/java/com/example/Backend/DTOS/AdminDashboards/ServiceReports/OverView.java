package com.example.Backend.DTOS.AdminDashboards.ServiceReports;

public class OverView {
    private Long totalService;
    private Long totalSkill;
    private Long ProvidersOfferingServices;

    public OverView(Long totalService, Long totalSkill, Long providersOfferingServices) {
        this.totalService = totalService;
        this.totalSkill = totalSkill;
        ProvidersOfferingServices = providersOfferingServices;
    }

    public Long getTotalService() {
        return totalService;
    }

    public void setTotalService(Long totalService) {
        this.totalService = totalService;
    }

    public Long getTotalSkill() {
        return totalSkill;
    }

    public void setTotalSkill(Long totalSkill) {
        this.totalSkill = totalSkill;
    }

    public Long getProvidersOfferingServices() {
        return ProvidersOfferingServices;
    }

    public void setProvidersOfferingServices(Long providersOfferingServices) {
        ProvidersOfferingServices = providersOfferingServices;
    }
}
