package com.example.Backend.DTOS.Bookings;

import java.time.LocalDate;

public class BookingListView {
    private Long id;
    private String userImage;
    private String userName;
    private String providerImage;
    private String providerName;
    private String service;
    private String status;
    private String createDate;

    public BookingListView(Long id, String userImage, String userName, String providerImage, String providerName, String service, String status, String createDate) {
        this.id = id;
        this.userImage = userImage;
        this.userName = userName;
        this.providerImage = providerImage;
        this.providerName = providerName;
        this.service = service;
        this.status = status;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProviderImage() {
        return providerImage;
    }

    public void setProviderImage(String providerImage) {
        this.providerImage = providerImage;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
