package com.example.Backend.DTOS.MobileProviders.RevenueAnalytics;

public class RevenueBreakdown {
    private String service;
    private int avg;

    public RevenueBreakdown(String service, int avg) {
        this.service = service;
        this.avg = avg;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int r) {
        this.avg = r;
    }
}
