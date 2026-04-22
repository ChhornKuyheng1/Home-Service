package com.example.Backend.DTOS.AdminDashboards.PlanReports;

public class PlanPriView {
    private Long totalSubscription;
    private Long status;
    private Long expired;
    private String top;

    public PlanPriView(Long totalSubscription, Long status, Long expired, String top) {
        this.totalSubscription = totalSubscription;
        this.status = status;
        this.expired = expired;
        this.top = top;
    }

    public Long getTotalSubscription() {
        return totalSubscription;
    }

    public void setTotalSubscription(Long totalSubscription) {
        this.totalSubscription = totalSubscription;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getExpired() {
        return expired;
    }

    public void setExpired(Long expired) {
        this.expired = expired;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }
}
