package com.example.Backend.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false)
    private  String name;
    private  String duration;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private int maxService;
    @Column(nullable = false)
    private int maxSkill;
    private String status;
    private String image;
    private LocalDate createDate;
    private String description;

    public Plan() {
    }

    public Plan(String name, String duration, float price, int maxService, int maxSkill, String status,LocalDate createDate, String description) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.maxService = maxService;
        this.maxSkill = maxSkill;
        this.status = status;
        this.createDate = createDate;
        this.description = description;
    }

    public Plan(String name, String duration, float price, int maxService, int maxSkill, String status, String description) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.maxService = maxService;
        this.maxSkill = maxSkill;
        this.status = status;
        this.description = description;
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
