package com.example.Backend.DTOS.Transactions;

import java.time.LocalDate;

public class TransactionList {
    private long id;
    private String name;
    private String tel;
    private String email;
    private String plan;
    private float total;
    private String date;
    private String status;
    private String image;

    public TransactionList() {
    }

    public TransactionList(long id, String name, String tel, String email, String plan, float total, String  date, String status,String image) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.plan = plan;
        this.total = total;
        this.date = date;
        this.status = status;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
