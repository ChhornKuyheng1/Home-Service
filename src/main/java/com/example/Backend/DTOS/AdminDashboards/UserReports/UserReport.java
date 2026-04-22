package com.example.Backend.DTOS.AdminDashboards.UserReports;

import com.example.Backend.DTOS.Bookings.BookingStatusCount;

import java.util.List;

public class UserReport {
    private List<CustomerGrowthPerMonth> growthPerMonths;
    private BookingCount bookingStatusCount;
    private List<UserReportList> userReportLists;

    public UserReport(List<CustomerGrowthPerMonth> growthPerMonths, BookingCount bookingStatusCount, List<UserReportList> userReportLists) {
        this.growthPerMonths = growthPerMonths;
        this.bookingStatusCount = bookingStatusCount;
        this.userReportLists = userReportLists;
    }

    public List<CustomerGrowthPerMonth> getGrowthPerMonths() {
        return growthPerMonths;
    }

    public void setGrowthPerMonths(List<CustomerGrowthPerMonth> growthPerMonths) {
        this.growthPerMonths = growthPerMonths;
    }

    public BookingCount getBookingStatusCount() {
        return bookingStatusCount;
    }

    public void setBookingStatusCount(BookingCount bookingStatusCount) {
        this.bookingStatusCount = bookingStatusCount;
    }

    public List<UserReportList> getUserReportLists() {
        return userReportLists;
    }

    public void setUserReportLists(List<UserReportList> userReportLists) {
        this.userReportLists = userReportLists;
    }
}
