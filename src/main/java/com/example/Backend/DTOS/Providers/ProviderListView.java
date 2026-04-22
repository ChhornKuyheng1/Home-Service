package com.example.Backend.DTOS.Providers;

import java.util.List;

public class ProviderListView {
    private Long id;
    private String name;
    private List<String> service;
    private String email;
    private String status;
    private String jobStatus;
    private String tel;
    private String image;
    public ProviderListView(Long id, String name, List<String> service, String email, String status, String jobStatus, String tel,String image) {
        this.id = id;
        this.name = name;
        this.service = service;
        this.email = email;
        this.status = status;
        this.jobStatus = jobStatus;
        this.tel = tel;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
