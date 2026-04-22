package com.example.Backend.DTOS.AdminDashboards.UserReports;

public class CustomerGrowthPerMonth {
    private Long month;
    private Long total;

    public CustomerGrowthPerMonth(Long month, Long total) {
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
