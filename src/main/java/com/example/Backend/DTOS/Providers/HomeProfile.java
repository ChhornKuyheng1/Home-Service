package com.example.Backend.DTOS.Providers;

import java.util.List;

public class HomeProfile {
    private String businessName;
    private String image;
    private List<String> topSkill;
    private double rate;
    private Long jobDone;
    private double avg;
    private boolean online;

    public HomeProfile(String businessName, String image,List<String> topSkill, double rate, Long jobDone, double avg, boolean online) {
        this.businessName = businessName;
        this.image = image;
        this.topSkill = topSkill;
        this.rate = rate;
        this.jobDone = jobDone;
        this.avg = avg;
        this.online = online;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTopSkill() {
        return topSkill;
    }

    public void setTopSkill(List<String> topSkill) {
        this.topSkill = topSkill;
    }

    public double getRate() {
        return  Math.round(rate*100.0) / 100.0;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Long getJobDone() {
        return jobDone;
    }

    public void setJobDone(Long jobDone) {
        this.jobDone = jobDone;
    }

    public double getAvg() {
        return Math.round(avg*100.0) / 100.0;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
