package com.example.Backend.DTOS.Users.MUsers;

import java.time.LocalDate;

public class UserBooking {
   private Long id;
   private String jobName;
   private String jonImage;
   private  String serviceName;
   private String providerName;
   private String providerImage;
   private LocalDate workDate;
   private String workTime;
   private String price;
   private Double serviceRadius;
   private String bookingDate;
   private String status;

    public UserBooking(Long id, String jobName, String jonImage, String serviceName, String providerName, String providerImage, LocalDate workDate, String workTime, String price, Double serviceRadius, String bookingDate, String status) {
        this.id = id;
        this.jobName = jobName;
        this.jonImage = jonImage;
        this.serviceName = serviceName;
        this.providerName = providerName;
        this.providerImage = providerImage;
        this.workDate = workDate;
        this.workTime = workTime;
        this.price = price;
        this.serviceRadius = serviceRadius;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJonImage() {
        return jonImage;
    }

    public void setJonImage(String jonImage) {
        this.jonImage = jonImage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderImage() {
        return providerImage;
    }

    public void setProviderImage(String providerImage) {
        this.providerImage = providerImage;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getServiceRadius() {
        return serviceRadius;
    }

    public void setServiceRadius(Double serviceRadius) {
        this.serviceRadius = serviceRadius;
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
