package com.example.Backend.DTOS.Bookings;

public class BookingReportList {
    private Long id;
    private String user;
    private String provider;
    private String skill;
    private String status;
    private String bookingDate;
    private String completeDate;
    private float amount;

    public BookingReportList(Long id, String user, String provider, String skill, String status, String bookingDate, String completeDate, float amount) {
        this.id = id;
        this.user = user;
        this.provider = provider;
        this.skill = skill;
        this.status = status;
        this.bookingDate = bookingDate;
        this.completeDate = completeDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
