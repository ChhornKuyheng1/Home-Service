package com.example.Backend.DTOS.Providers;

import org.apache.catalina.LifecycleState;

import java.util.List;

public class ProviderApproveDto {
    private Long id;
    private String name;
    private String image;
    private List<String> services;
    private double rate;
    private Long job;
    private String email;
    private String tel;

    public ProviderApproveDto(Long id, String name, String image, List<String> services, double rate, Long job, String email, String tel) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.services = services;
        this.rate = rate;
        this.job = job;
        this.email = email;
        this.tel = tel;
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

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Long getJob() {
        return job;
    }

    public void setJob(Long job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
