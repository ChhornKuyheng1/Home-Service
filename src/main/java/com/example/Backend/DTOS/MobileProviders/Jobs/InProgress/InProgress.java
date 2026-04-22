package com.example.Backend.DTOS.MobileProviders.Jobs.InProgress;

import com.example.Backend.DTOS.MobileProviders.Jobs.BookingList;
import com.example.Backend.Models.Booking;

import java.util.List;

public class InProgress {
    private List<ServiceDto> service;
    private List<BookingList> booking;

    public InProgress(List<ServiceDto> service, List<BookingList> booking) {
        this.service = service;
        this.booking = booking;
    }

    public List<ServiceDto> getService() {
        return service;
    }

    public void setService(List<ServiceDto> service) {
        this.service = service;
    }

    public List<BookingList> getBooking() {
        return booking;
    }

    public void setBooking(List<BookingList> booking) {
        this.booking = booking;
    }
}
