package com.example.Backend.Requests;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class UserUpdateRequest {
    private Long userId;
    private String name;
    private String tel;
    private LocalDate dob;
    private MultipartFile image;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
