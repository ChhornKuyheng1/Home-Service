package com.example.Backend.DTOS.MobileProviders.Services;

public class ProviderJobFocus {
    private Long id;
    private String name;
    private double price1;
    private double price2;
    private long duration;
    private String description;
    private String status;
    private String image;

    public ProviderJobFocus(Long id, String name, double price1, double price2, long duration, String description, String status, String image) {
        this.id = id;
        this.name = name;
        this.price1 = price1;
        this.price2 = price2;
        this.duration = duration;
        this.description = description;
        this.status = status;
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

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
