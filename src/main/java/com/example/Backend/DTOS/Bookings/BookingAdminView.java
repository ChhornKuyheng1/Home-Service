package com.example.Backend.DTOS.Bookings;

public class BookingAdminView {
    private BookingInfo bookingInfo;
    private ServiceDetail serviceDetail;
    private ProviderDto providerInfo;
    private UserDto userInfo;

    public BookingAdminView(BookingInfo bookingInfo, ServiceDetail serviceDetail, ProviderDto providerInfo, UserDto userInfo) {
        this.bookingInfo = bookingInfo;
        this.serviceDetail = serviceDetail;
        this.providerInfo = providerInfo;
        this.userInfo = userInfo;
    }

    public BookingInfo getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public ServiceDetail getServiceDetail() {
        return serviceDetail;
    }

    public void setServiceDetail(ServiceDetail serviceDetail) {
        this.serviceDetail = serviceDetail;
    }

    public ProviderDto getProviderInfo() {
        return providerInfo;
    }

    public void setProviderInfo(ProviderDto providerInfo) {
        this.providerInfo = providerInfo;
    }

    public UserDto getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDto userInfo) {
        this.userInfo = userInfo;
    }
}
