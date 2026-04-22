package com.example.Backend.Requests;

import org.springframework.web.multipart.MultipartFile;

public class ProviderProfile {
    private Long providerId;
    private MultipartFile image;

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
