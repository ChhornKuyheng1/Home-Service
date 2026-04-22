package com.example.Backend.DTOS.AdminDashboards.ProviderReports;

public class ProviderListReport {

    private Long id;
    private String name;
    private String tel;
    private String email;
    private Long booked;
    private Long complete;
    private Long cancel;
    private String joinDate;

    public ProviderListReport(Long id, String name, String tel, String email, Long booked, Long complete, Long cancel, String joinDate) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.booked = booked;
        this.complete = complete;
        this.cancel = cancel;
        this.joinDate = joinDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBooked() {
        return booked;
    }

    public void setBooked(Long booked) {
        this.booked = booked;
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

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
