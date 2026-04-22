package com.example.Backend.DTOS;

public class PlanToUserDto {
    private Long id;
    private String name;
    private String duration;
    private float price;
    private int maxService;
    private int maxSkill;
    private String image;

    public PlanToUserDto(Long id, String name, String duration, float price, int maxService, int maxSkill, String image) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.maxService = maxService;
        this.maxSkill = maxSkill;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMaxService() {
        return maxService;
    }

    public void setMaxService(int maxService) {
        this.maxService = maxService;
    }

    public int getMaxSkill() {
        return maxSkill;
    }

    public void setMaxSkill(int maxSkill) {
        this.maxSkill = maxSkill;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
