package com.example.Backend.DTOS.Transactions;

import java.time.LocalDate;

public class TransactionDetail {
    private Long id;
    private String status;
    private String payDate;
    private double amount;

    public TransactionDetail(Long id, String status, String payDate, double amount) {
        this.id = id;
        this.status = status;
        this.payDate = payDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
