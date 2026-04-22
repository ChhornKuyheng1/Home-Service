package com.example.Backend.DTOS;

import java.time.LocalDate;

public class WorkTime {
    private LocalDate date;
    private Long total;

    public WorkTime(LocalDate date, Long total) {
        this.date = date;
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
