package com.example.Backend.DTOS.AdminDashboards.PlanReports;

import java.math.BigDecimal;

public class PlanReportList {

    private Long id;
    private String name;
    private float price;
    private Long total;
    private Long active;
    private Long expired;
    private Double revenue;

    public PlanReportList(Long id, String name, float price, Long total,Long active,Long expired,Double revenue) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.total = total;
        this.revenue = revenue;
        this.active = active;
        this.expired = expired;
    }

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public Long getTotal() {
        return total;
    }

    public Long getActive() {
        return active;
    }

    public Long getExpired() {
        return expired;
    }

    public Double getRevenue() {
        return Math.round(this.revenue*100.0) / 100.0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public void setExpired(Long expired) {
        this.expired = expired;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}

