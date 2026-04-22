package com.example.Backend.DTOS.AdminDashboards.ProviderReports;

public class ProviderStatusCount {
    private Long total;
    private Long approved;
    private Long pending;
    private Long rejected;

    public ProviderStatusCount(Long total, Long approved, Long pending, Long rejected) {
        this.total = total;
        this.approved = approved;
        this.pending = pending;
        this.rejected = rejected;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getApproved() {
        return approved;
    }

    public void setApproved(Long approved) {
        this.approved = approved;
    }

    public Long getPending() {
        return pending;
    }

    public void setPending(Long pending) {
        this.pending = pending;
    }

    public Long getRejected() {
        return rejected;
    }

    public void setRejected(Long rejected) {
        this.rejected = rejected;
    }
}
