package com.example.Backend.DTOS;

public class AvailableTime {
    private boolean available;
    private long time;

    public AvailableTime(boolean available, long time) {
        this.available = available;
        this.time = time;
    }

    public AvailableTime() {
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
