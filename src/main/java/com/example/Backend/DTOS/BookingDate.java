package com.example.Backend.DTOS;

import java.time.LocalDate;

public class BookingDate {
    private LocalDate workDate;
    private Long time;

    public BookingDate(LocalDate workDate, Long time) {
        this.workDate = workDate;
        this.time = time;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
