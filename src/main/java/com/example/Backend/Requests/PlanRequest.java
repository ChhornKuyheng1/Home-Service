package com.example.Backend.Requests;

import org.springframework.web.multipart.MultipartFile;

public class PlanRequest {
    private String name;
    private String status;
    private int maxService;
    private int maxSkill;
    private float price;
    private String duration;
    private MultipartFile image;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
