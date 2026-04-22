package com.example.Backend.DTOS.AdminDashboards;

public class RecentBooking {
    private Long id;
    private String providerName;
    private String bookingDate;
    private String status;
    private String image;
    private String statusBooking;

    public RecentBooking(Long id, String providerName, String bookingDate, String status, String image,String s) {
        this.id = id;
        this.providerName = providerName;
        this.bookingDate = bookingDate;
        this.status = status;
        this.image = image;
        this.statusBooking = s;
    }

    public String getStatusBooking() {
        return statusBooking;
    }

    public void setStatusBooking(String statusBooking) {
        this.statusBooking = statusBooking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
