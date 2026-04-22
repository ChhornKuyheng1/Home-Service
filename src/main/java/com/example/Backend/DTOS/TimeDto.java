package com.example.Backend.DTOS;

import java.time.LocalTime;

public class TimeDto {
    private LocalTime time;
    private String name;

    public TimeDto(LocalTime time, String name) {
        this.time = time;
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
