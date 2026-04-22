package com.example.Backend.DTOS.MobileProviders.Services;

import com.example.Backend.Models.JobFocus;
import com.example.Backend.Models.Provider;

import java.util.List;

public class ProviderJob {
    private Long id;
    private String name;
    private String status;
    private String image;
    private List<ProviderJobFocus> jobFocus;

    public ProviderJob(Long id, String name, String status, String image, List<ProviderJobFocus> jobFocus) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.image = image;
        this.jobFocus = jobFocus;
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

    public List<ProviderJobFocus> getJobFocus() {
        return jobFocus;
    }

    public void setJobFocus(List<ProviderJobFocus> jobFocus) {
        this.jobFocus = jobFocus;
    }
}
