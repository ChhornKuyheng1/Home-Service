package com.example.Backend.DTOS.Users.MUsers;

public class ProviderBookingUserView {
    private String name;
    private String image;
    private double rate;
    private Long totalReview;
    private String tel;

    public ProviderBookingUserView(String name, String image, double rate, Long totalReview, String tel) {
        this.name = name;
        this.image = image;
        this.rate = rate;
        this.totalReview = totalReview;
        this.tel = tel;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Long getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(Long totalReview) {
        this.totalReview = totalReview;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
