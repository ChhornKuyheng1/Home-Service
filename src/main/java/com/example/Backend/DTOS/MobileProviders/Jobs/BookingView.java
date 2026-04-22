package com.example.Backend.DTOS.MobileProviders.Jobs;

import com.example.Backend.DTOS.AddressDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingView {
    private Long id;
    private JobBookingView job;
    private UserBookingView user;
    private AddressDto address;
    private LocalDate workDate;
    private String time;
    private String requestDate;
    private String confirmDate;
    private String inProgressDate;
    private String completeDate;

    public BookingView(Long id, JobBookingView job, UserBookingView user, AddressDto address, LocalDate date, String time, String requestDate, String confirmDate, String inProgressDate, String completeDate) {
        this.id = id;
        this.job = job;
        this.user = user;
        this.address = address;
        this.workDate = date;
        this.time = time;
        this.requestDate = requestDate;
        this.confirmDate = confirmDate;
        this.inProgressDate = inProgressDate;
        this.completeDate = completeDate;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getInProgressDate() {
        return inProgressDate;
    }

    public void setInProgressDate(String inProgressDate) {
        this.inProgressDate = inProgressDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
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
}
