package com.example.Backend.DTOS.Providers;

import java.time.LocalDate;

public class ProviderDetail {
    private String name;
    private String status;
    private String jobStatus;
    private String joinDate;
    private String image;

    public ProviderDetail(String name, String status, String jobStatus, String joinDate, String image) {
        this.name = name;
        this.status = status;
        this.jobStatus = jobStatus;
        this.joinDate = joinDate;
        this.image = image;
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

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
