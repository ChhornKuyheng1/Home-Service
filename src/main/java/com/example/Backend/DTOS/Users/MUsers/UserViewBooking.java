package com.example.Backend.DTOS.Users.MUsers;

import com.example.Backend.DTOS.AddressDto;

import java.time.LocalDate;

public class UserViewBooking {
    private Long bookingId;
    private String requestDate;
    private String confirmDate;
    private String inProgressDate;
    private String complete;
    private ServiceDetailUserBookingView serviceDetail;
    private ProviderBookingUserView providerDetail;
    private LocalDate workDate;
    private String workTime;
    private AddressDto serviceLocation;

    public UserViewBooking(Long bookingId, String requestDate, String confirmDate, String inProgressDate, ServiceDetailUserBookingView serviceDetail, ProviderBookingUserView providerDetail, LocalDate workDate, String workTime, AddressDto serviceLocation,String complete) {
        this.bookingId = bookingId;
        this.requestDate = requestDate;
        this.confirmDate = confirmDate;
        this.inProgressDate = inProgressDate;
        this.serviceDetail = serviceDetail;
        this.providerDetail = providerDetail;
        this.workDate = workDate;
        this.workTime = workTime;
        this.serviceLocation = serviceLocation;
        this.complete =complete;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public ServiceDetailUserBookingView getServiceDetail() {
        return serviceDetail;
    }

    public void setServiceDetail(ServiceDetailUserBookingView serviceDetail) {
        this.serviceDetail = serviceDetail;
    }

    public ProviderBookingUserView getProviderDetail() {
        return providerDetail;
    }

    public void setProviderDetail(ProviderBookingUserView providerDetail) {
        this.providerDetail = providerDetail;
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

    public AddressDto getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(AddressDto serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getInProgressDate() {
        return inProgressDate;
    }

    public void setInProgressDate(String inProgressDate) {
        this.inProgressDate = inProgressDate;
    }
}
