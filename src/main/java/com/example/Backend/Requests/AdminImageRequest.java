package com.example.Backend.Requests;

import org.springframework.web.multipart.MultipartFile;

public class AdminImageRequest {
    private Long AdminId;
    private MultipartFile image;

    public Long getAdminId() {
        return AdminId;
    }

    public void setAdminId(Long adminId) {
        AdminId = adminId;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
