package com.example.Backend.DTOS.AdminDashboards.ProviderReports;

public class ProviderGrowthPerMonth {
    private Long month;
    private Long total;

    public ProviderGrowthPerMonth(Long month, Long total) {
        this.month = month;
        this.total = total;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
