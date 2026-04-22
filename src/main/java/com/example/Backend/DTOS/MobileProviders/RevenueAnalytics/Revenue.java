package com.example.Backend.DTOS.MobileProviders.RevenueAnalytics;

import java.util.List;

public class Revenue {
    private TotalRevenue totalRevenue;
    private List<TopServiceRevenue> topService;
    private List<RevenueBreakdown> breakdowns;

    public Revenue(TotalRevenue totalRevenue, List<TopServiceRevenue> topService, List<RevenueBreakdown> breakdowns) {
        this.totalRevenue = totalRevenue;
        this.topService = topService;
        this.breakdowns = breakdowns;
    }

    public List<TopServiceRevenue> getTopService() {
        return topService;
    }

    public void setTopService(List<TopServiceRevenue> topService) {
        this.topService = topService;
    }

    public TotalRevenue getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(TotalRevenue totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<RevenueBreakdown> getBreakdowns() {
        return breakdowns;
    }

    public void setBreakdowns(List<RevenueBreakdown> breakdowns) {
        this.breakdowns = breakdowns;
    }
}
