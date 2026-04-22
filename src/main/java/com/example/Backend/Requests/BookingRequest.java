package com.example.Backend.Requests;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequest {
    private Long userId;
    private Long jobFocusId;
    private LocalDate workDate;
    private LocalTime workTime;
    private Long addressId;
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobFocusId() {
        return jobFocusId;
    }

    public void setJobFocusId(Long jobFocusId) {
        this.jobFocusId = jobFocusId;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public LocalTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(LocalTime workTime) {
        this.workTime = workTime;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
