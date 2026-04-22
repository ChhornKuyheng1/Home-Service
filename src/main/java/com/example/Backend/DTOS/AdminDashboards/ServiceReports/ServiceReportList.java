package com.example.Backend.DTOS.AdminDashboards.ServiceReports;

public class ServiceReportList {
    private Long id;
    private String skill;
    private String service;
    private Long provider;
    private Long booking;
    private Long complete;
    private Long cancel;

    public ServiceReportList(Long id, String skill, String service, Long provider, Long booking, Long complete, Long cancel) {
        this.id = id;
        this.skill = skill;
        this.service = service;
        this.provider = provider;
        this.booking = booking;
        this.complete = complete;
        this.cancel = cancel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Long getProvider() {
        return provider;
    }

    public void setProvider(Long provider) {
        this.provider = provider;
    }

    public Long getBooking() {
        return booking;
    }

    public void setBooking(Long booking) {
        this.booking = booking;
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
