package com.example.Backend.DTOS.MobileProviders.ProviderViewDetails.Services;

public class SkillDetail {
    private Long id;
    private String name;
    private int duration;
    private String price;
    private String image;

    public SkillDetail(Long id, String name, int duration, String price, String image) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
