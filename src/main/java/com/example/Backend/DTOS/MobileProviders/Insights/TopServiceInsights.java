package com.example.Backend.DTOS.MobileProviders.Insights;

public class TopServiceInsights {
    private String service;
    private Long booking;

    public TopServiceInsights(String service, Long booking) {
        this.service = service;
        this.booking = booking;
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
}
