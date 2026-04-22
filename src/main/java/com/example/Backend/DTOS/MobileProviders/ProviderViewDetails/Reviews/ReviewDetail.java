package com.example.Backend.DTOS.MobileProviders.ProviderViewDetails.Reviews;

import java.time.LocalDate;
import java.util.List;

public class ReviewDetail {
    private String userName;
    private String serviceName;
    private int rate;
    private String review;
    List<String> reviewImage;
    private String userImage;
    private LocalDate reviewDate;

    public ReviewDetail(String userName, String serviceName, int rate, String review, List<String> reviewImage, String userImage, LocalDate reviewDate) {
        this.userName = userName;
        this.serviceName = serviceName;
        this.rate = rate;
        this.review = review;
        this.reviewImage = reviewImage;
        this.userImage = userImage;
        this.reviewDate = reviewDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public List<String> getReviewImage() {
        return reviewImage;
    }

    public void setReviewImage(List<String> reviewImage) {
        this.reviewImage = reviewImage;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
