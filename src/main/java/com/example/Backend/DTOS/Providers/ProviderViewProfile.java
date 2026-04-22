package com.example.Backend.DTOS.Providers;

public class ProviderViewProfile {
    private String businessName;
    private String name;
    private double rate;
    private Long totalJob;
    private Long totalService;

    public ProviderViewProfile(String businessName, String name, double rate, Long totalJob, Long totalService) {
        this.businessName = businessName;
        this.name = name;
        this.rate = rate;
        this.totalJob = totalJob;
        this.totalService = totalService;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Long getTotalJob() {
        return totalJob;
    }

    public void setTotalJob(Long totalJob) {
        this.totalJob = totalJob;
    }

    public Long getTotalService() {
        return totalService;
    }

    public void setTotalService(Long totalService) {
        this.totalService = totalService;
    }
}
