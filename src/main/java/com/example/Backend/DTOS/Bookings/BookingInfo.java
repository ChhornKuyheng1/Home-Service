package com.example.Backend.DTOS.Bookings;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingInfo {
    private Long id;
    private String status;
    private LocalDate createDate;
    private String createTime;

    public BookingInfo(Long id, String status, LocalDate createDate, String createTime) {
        this.id = id;
        this.status = status;
        this.createDate = createDate;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String  getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
