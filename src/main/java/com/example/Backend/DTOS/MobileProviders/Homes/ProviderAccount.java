package com.example.Backend.DTOS.MobileProviders.Homes;

public class ProviderAccount {
    private String name;
    private String image;
    private Long jobDone;
    private double avgValue;
    private double rating;

    public ProviderAccount(String name, String image, Long jobDone, double avgValue, double rating) {
        this.name = name;
        this.image = image;
        this.jobDone = jobDone;
        this.avgValue = avgValue;
        this.rating = rating;
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

    public Long getJobDone() {
        return jobDone;
    }

    public void setJobDone(Long jobDone) {
        this.jobDone = jobDone;
    }

    public double getAvgValue() {
        return avgValue;
    }

    public void setAvgValue(double avgValue) {
        this.avgValue = avgValue;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
