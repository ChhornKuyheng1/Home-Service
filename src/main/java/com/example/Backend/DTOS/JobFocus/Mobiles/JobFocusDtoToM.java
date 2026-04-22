package com.example.Backend.DTOS.JobFocus.Mobiles;

public class JobFocusDtoToM {
    private Long id;
    private String name;
    private double rate;
    private Long review;
    private String price;
    private String description;
    private String image;

    public JobFocusDtoToM(Long id, String name, double rate, Long review, String price, String description, String image) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.review = review;
        this.price = price;
        this.description = description;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
