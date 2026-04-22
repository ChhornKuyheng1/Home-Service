package com.example.Backend.DTOS.MobileProviders.RevenueAnalytics;

public class TotalRevenue {
    private double total;
    private double avg;
    private double highest;

    public TotalRevenue(double total, double avg, double highest) {
        this.total = total;
        this.avg = avg;
        this.highest = highest;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getHighest() {
        return highest;
    }

    public void setHighest(double highest) {
        this.highest = highest;
    }
}
