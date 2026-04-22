package com.example.Backend.DTOS;

public class PlanDto {
    private Long id;
    private String name;
    private String duration;
    private String price;
    private int maxService;
    private int maxSkill;
    private String status;
    private String image;

    public PlanDto(Long id, String name, String duration, int maxService, int maxSkill, String status, String image) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.maxService = maxService;
        this.maxSkill = maxSkill;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
