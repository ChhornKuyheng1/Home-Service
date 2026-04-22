package com.example.Backend.DTOS.Providers;

import java.util.List;

public class ProviderNearView {
    private Long id;
    private String name;
    private List<String> service;
    private double rate;
    private double serviceRadius;
    private Long totalBooking;
    private String image;
    public ProviderNearView(Long id, String name,String image,double km) {
        this.id = id;
        this.name = name;
        this.serviceRadius = km;
        this.image = image;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getService() {
        return service;
    }

    public void setService(List<String> service) {
        this.service = service;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getServiceRadius() {
        return Math.round(serviceRadius*100.0) / 100.0;
    }

    public void setServiceRadius(double serviceRadius) {
        this.serviceRadius = serviceRadius;
    }

    public Long getTotalBooking() {
        return totalBooking;
    }

    public void setTotalBooking(Long totalBooking) {
        this.totalBooking = totalBooking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
