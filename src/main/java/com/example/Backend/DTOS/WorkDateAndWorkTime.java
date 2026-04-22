package com.example.Backend.DTOS;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkDateAndWorkTime {

    private LocalDate date;
    private LocalTime time;
    private long duration;

    public WorkDateAndWorkTime(LocalDate date, LocalTime time, long duration) {
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
