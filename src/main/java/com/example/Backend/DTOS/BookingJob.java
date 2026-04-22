package com.example.Backend.DTOS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingJob {
    private LocalDate date;
    private LocalTime workTime;
    private long duration;

    public BookingJob(LocalDate date, LocalTime workTime, long duration) {
        this.date = date;
        this.workTime = workTime;
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(LocalTime workTime) {
        this.workTime = workTime;
    }
}
