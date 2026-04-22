package com.example.Backend.DTOS.Providers;

import java.time.LocalDate;

public class ProviderPending {
    private Long id;
    private String name;
    private String email;
    private String tel;
    private LocalDate dob;
    private String image;
    private String requestDate;
    private String status;

    public ProviderPending(Long id, String name, String email, String tel, LocalDate dob, String image, String requestDate,String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.dob = dob;
        this.image = image;
        this.requestDate = requestDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
}
