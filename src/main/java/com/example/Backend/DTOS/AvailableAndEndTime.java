package com.example.Backend.DTOS;

import java.time.LocalTime;

public class AvailableAndEndTime {
    private boolean available;
    private LocalTime end;

    public AvailableAndEndTime(boolean available, LocalTime end) {
        this.available = available;
        this.end = end;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}
