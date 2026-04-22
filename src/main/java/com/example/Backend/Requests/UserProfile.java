package com.example.Backend.Requests;

import org.springframework.web.multipart.MultipartFile;

public class UserProfile {
    private Long id;
    private MultipartFile image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
