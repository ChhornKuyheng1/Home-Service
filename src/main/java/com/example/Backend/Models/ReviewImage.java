package com.example.Backend.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ReviewImages")
public class ReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false,name = "review_id")
    private Review review;
    private String image;
    private LocalDate date;

    public ReviewImage() {
    }

    public ReviewImage(Review review, String image, LocalDate date) {
        this.review = review;
        this.image = image;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
