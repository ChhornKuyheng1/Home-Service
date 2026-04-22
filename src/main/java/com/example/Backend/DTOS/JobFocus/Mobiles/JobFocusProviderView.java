package com.example.Backend.DTOS.JobFocus.Mobiles;

public class JobFocusProviderView {
    private Long id;
    private String name;
    private double rate;
    private Long review;
    private String image;

    public JobFocusProviderView(String name, double rate, Long review, String image,Long id) {
        this.name = name;
        this.rate = rate;
        this.review = review;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Long getReview() {
        return review;
    }

    public void setReview(Long review) {
        this.review = review;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
