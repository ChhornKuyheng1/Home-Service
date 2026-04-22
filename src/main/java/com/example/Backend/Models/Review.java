package com.example.Backend.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Reviews")
public class Review {
    @Id
    private Long id;
    @MapsId
    @OneToOne
    @JoinColumn(nullable = false)
    private Booking booking;
    private String review;
    @Column(nullable = false)
    private int rate;
    private String response;
    private LocalDate date;

    public Review() {
    }

    public Review(Booking booking, String review, int rate, LocalDate date) {
        this.booking = booking;
        this.review = review;
        this.rate = rate;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
