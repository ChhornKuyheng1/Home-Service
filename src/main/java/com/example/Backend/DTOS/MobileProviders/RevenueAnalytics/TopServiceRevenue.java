package com.example.Backend.DTOS.MobileProviders.RevenueAnalytics;

public class TopServiceRevenue {
    private String service;
    private Long booking;
    private double total;

    public TopServiceRevenue(String service, Long booking, double total) {
        this.service = service;
        this.booking = booking;
        this.total = total;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Long getBooking() {
        return booking;
    }

    public void setBooking(Long booking) {
        this.booking = booking;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
