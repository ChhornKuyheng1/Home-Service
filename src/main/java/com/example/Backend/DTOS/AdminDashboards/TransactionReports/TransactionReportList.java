package com.example.Backend.DTOS.AdminDashboards.TransactionReports;

import java.time.LocalDate;

public class TransactionReportList {
    private long id;
    private String name;
    private String tel;
    private String email;
    private String plan;
    private float total;
    private String date;
    private String status;

    public TransactionReportList(long id, String name, String tel, String email, String plan, float total, String date, String status) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.plan = plan;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
