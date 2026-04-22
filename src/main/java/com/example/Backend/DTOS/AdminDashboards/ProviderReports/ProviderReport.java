package com.example.Backend.DTOS.AdminDashboards.ProviderReports;

import org.apache.catalina.LifecycleState;

import java.util.List;

public class ProviderReport {
    private List<ProviderListReport> providerLists;
    private List<ProviderGrowthPerMonth> growthPerMonths;
    private ProviderStatusCount statusCount;

    public ProviderReport(List<ProviderListReport> providerLists, List<ProviderGrowthPerMonth> growthPerMonths, ProviderStatusCount statusCount) {
        this.providerLists = providerLists;
        this.growthPerMonths = growthPerMonths;
        this.statusCount = statusCount;
    }

    public List<ProviderListReport> getProviderLists() {
        return providerLists;
    }

    public void setProviderLists(List<ProviderListReport> providerLists) {
        this.providerLists = providerLists;
    }

    public List<ProviderGrowthPerMonth> getGrowthPerMonths() {
        return growthPerMonths;
    }

    public void setGrowthPerMonths(List<ProviderGrowthPerMonth> growthPerMonths) {
        this.growthPerMonths = growthPerMonths;
    }

    public ProviderStatusCount getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(ProviderStatusCount statusCount) {
        this.statusCount = statusCount;
    }
}
