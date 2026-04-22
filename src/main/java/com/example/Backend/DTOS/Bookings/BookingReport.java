package com.example.Backend.DTOS.Bookings;


import java.util.List;

public class BookingReport {

    private List<BookingPerMonth> bookingPerMonths;
    private List<BookingReportList> bookingLists;
    private BookingStatusCount bookingStatusCount;

    public BookingReport(List<BookingPerMonth> bookingPerMonths, List<BookingReportList> bookingLists, BookingStatusCount bookingStatusCount) {
        this.bookingPerMonths = bookingPerMonths;
        this.bookingLists = bookingLists;
        this.bookingStatusCount = bookingStatusCount;
    }

    public List<BookingPerMonth> getBookingPerMonths() {
        return bookingPerMonths;
    }

    public void setBookingPerMonths(List<BookingPerMonth> bookingPerMonths) {
        this.bookingPerMonths = bookingPerMonths;
    }

    public List<BookingReportList> getBookingLists() {
        return bookingLists;
    }

    public void setBookingLists(List<BookingReportList> bookingLists) {
        this.bookingLists = bookingLists;
    }

    public BookingStatusCount getBookingStatusCount() {
        return bookingStatusCount;
    }

    public void setBookingStatusCount(BookingStatusCount bookingStatusCount) {
        this.bookingStatusCount = bookingStatusCount;
    }
}
