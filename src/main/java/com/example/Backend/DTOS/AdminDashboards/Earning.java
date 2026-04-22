package com.example.Backend.DTOS.AdminDashboards;

public class Earning {
    private Long month;
    private double total;

    public Earning(Long month, double total) {
        this.month = month;
        this.total = total;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
