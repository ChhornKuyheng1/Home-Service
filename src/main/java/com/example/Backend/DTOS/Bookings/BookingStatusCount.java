package com.example.Backend.DTOS.Bookings;

public class BookingStatusCount {
    private  Long request;
    private Long confirm;
    private Long inProgress;
    private Long complete;
    private Long cancel;

    public BookingStatusCount(Long request, Long confirm, Long inProgress, Long complete,Long c) {
        this.request = request;
        this.confirm = confirm;
        this.inProgress = inProgress;
        this.complete = complete;
        this.cancel = c;
    }

    public Long getCancel() {
        return cancel;
    }

    public void setCancel(Long cancel) {
        this.cancel = cancel;
    }

    public Long getRequest() {
        return request;
    }

    public void setRequest(Long request) {
        this.request = request;
    }

    public Long getConfirm() {
        return confirm;
    }

    public void setConfirm(Long confirm) {
        this.confirm = confirm;
    }

    public Long getInProgress() {
        return inProgress;
    }

    public void setInProgress(Long inProgress) {
        this.inProgress = inProgress;
    }

    public Long getComplete() {
        return complete;
    }

    public void setComplete(Long complete) {
        this.complete = complete;
    }
}
