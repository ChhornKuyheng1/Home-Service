package com.example.Backend.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "JobFocus")
public class JobFocus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Job job;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Skill skill;
    private double price1;
    private double price2;
    private long duration;
    private String status;
    private boolean top;
    private String description;
    private LocalDate createDate;

    public JobFocus(Job job, Skill skill, double price1, double price2, long duration, String status, String description, LocalDate createDate) {
        this.job = job;
        this.skill = skill;
        this.price1 = price1;
        this.price2 = price2;
        this.duration = duration;
        this.status = status;
        this.description = description;
        this.createDate = createDate;
    }

    public JobFocus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public double getPrice() {
        return price1;
    }

    public void setPrice(double price) {
        this.price1 = price;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }
}
