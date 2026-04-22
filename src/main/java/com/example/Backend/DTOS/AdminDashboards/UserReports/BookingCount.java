package com.example.Backend.DTOS.AdminDashboards.UserReports;

public class BookingCount {
    private Long total;
    private Long complete;
    private Long cancel;

    public BookingCount(Long total, Long complete, Long cancel) {
        this.total = total;
        this.complete = complete;
        this.cancel = cancel;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getComplete() {
        return complete;
    }

    public void setComplete(Long complete) {
        this.complete = complete;
    }

    public Long getCancel() {
        return cancel;
    }

    public void setCancel(Long cancel) {
        this.cancel = cancel;
    }
}
