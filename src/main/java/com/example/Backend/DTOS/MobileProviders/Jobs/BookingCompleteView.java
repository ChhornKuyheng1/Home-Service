package com.example.Backend.DTOS.MobileProviders.Jobs;

import com.example.Backend.DTOS.AddressDto;
import com.example.Backend.DTOS.Reviews.Providers.ProviderReview;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingCompleteView {
    private Long id;
    private JobBookingView job;
    private UserBookingView user;
    private AddressDto address;
    private LocalDate workDate;
    private String time;
    private ProviderReview review;
    private String requestDate;
    private String confirmDate;
    private String inProgressData;
    private String completeDate;

    public BookingCompleteView(Long id, JobBookingView job, UserBookingView user, AddressDto address, LocalDate date, String time, ProviderReview review, String confirmDate, String inProgressData, String completeDate,String requestDate) {
        this.id = id;
        this.job = job;
        this.user = user;
        this.address = address;
        this.workDate = date;
        this.time = time;
        this.review = review;
        this.confirmDate = confirmDate;
        this.inProgressData = inProgressData;
        this.completeDate = completeDate;
        this.requestDate = requestDate;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobBookingView getJob() {
        return job;
    }

    public void setJob(JobBookingView job) {
        this.job = job;
    }

    public UserBookingView getUser() {
        return user;
    }

    public void setUser(UserBookingView user) {
        this.user = user;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ProviderReview getReview() {
        return review;
    }

    public void setReview(ProviderReview review) {
        this.review = review;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getInProgressData() {
        return inProgressData;
    }

    public void setInProgressData(String inProgressData) {
        this.inProgressData = inProgressData;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }
}
