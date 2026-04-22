package com.example.Backend.DTOS.Reviews.Providers;

import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public class ProviderReview {
    private Long id;
    private int rate;
    private String review;
    private List<String> image;
    private String response;

    public ProviderReview() {
    }

    public ProviderReview(Long id, int rate, String review, List<String> image, String response) {
        this.id = id;
        this.rate = rate;
        this.review = review;
        this.image = image;
        this.response = response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
