package com.example.Backend.DTOS.AdminDashboards.UserReports;

public class UserReportList {
    private Long id;
    private String name;
    private String tel;
    private Long totalBooking;
    private Long totalComplete;
    private Long totalCancel;
    private String joinDate;

    public UserReportList(Long id, String name, String tel, Long totalBooking, Long totalComplete, Long totalCancel, String joinDate) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.totalBooking = totalBooking;
        this.totalComplete = totalComplete;
        this.totalCancel = totalCancel;
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

    public Long getTotalBooking() {
        return totalBooking;
    }

    public void setTotalBooking(Long totalBooking) {
        this.totalBooking = totalBooking;
    }

    public Long getTotalComplete() {
        return totalComplete;
    }

    public void setTotalComplete(Long totalComplete) {
        this.totalComplete = totalComplete;
    }

    public Long getTotalCancel() {
        return totalCancel;
    }

    public void setTotalCancel(Long totalCancel) {
        this.totalCancel = totalCancel;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
