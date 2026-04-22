package com.example.Backend.DTOS.Providers;

public class ProviderNear {
    private Long id;
    private String name;
    private  String image;
    private Double rate;
    private boolean isOnline;

    public ProviderNear(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public ProviderNear(Long id, String name, String image, Double rate, boolean isOnline) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.rate = rate;
        this.isOnline = isOnline;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
