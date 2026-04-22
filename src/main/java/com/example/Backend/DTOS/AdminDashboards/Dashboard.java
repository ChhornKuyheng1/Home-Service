package com.example.Backend.DTOS.AdminDashboards;

import java.util.List;

public class Dashboard {
    private double totalEarning;
    private Long totalProvider;
    private Long totalUser;
    private Long totalBooking;
    private List<Earning> earnings;
    private List<ProviderRequestViewDashboard> providerRequests;
    private List<RecentBooking> recentBookings;

    public Dashboard(double totalEarning, Long totalProvider, Long totalUser, Long totalBooking, List<Earning> earnings, List<ProviderRequestViewDashboard> providerRequests, List<RecentBooking> recentBookings) {
        this.totalEarning = totalEarning;
        this.totalProvider = totalProvider;
        this.totalUser = totalUser;
        this.totalBooking = totalBooking;
        this.earnings = earnings;
        this.providerRequests = providerRequests;
        this.recentBookings = recentBookings;
    }

    public List<RecentBooking> getRecentBookings() {
        return recentBookings;
    }

    public void setRecentBookings(List<RecentBooking> recentBookings) {
        this.recentBookings = recentBookings;
    }

    public List<ProviderRequestViewDashboard> getProviderRequests() {
        return providerRequests;
    }

    public void setProviderRequests(List<ProviderRequestViewDashboard> providerRequests) {
        this.providerRequests = providerRequests;
    }

    public double getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(double totalEarning) {
        this.totalEarning = totalEarning;
    }

    public Long getTotalProvider() {
        return totalProvider;
    }

    public void setTotalProvider(Long totalProvider) {
        this.totalProvider = totalProvider;
    }

    public Long getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(Long totalUser) {
        this.totalUser = totalUser;
    }

    public Long getTotalBooking() {
        return totalBooking;
    }

    public void setTotalBooking(Long totalBooking) {
        this.totalBooking = totalBooking;
    }

    public List<Earning> getEarnings() {
        return earnings;
    }

    public void setEarnings(List<Earning> earnings) {
        this.earnings = earnings;
    }
}
