package com.example.Backend.Requests;

import org.springframework.web.multipart.MultipartFile;

public class ProviderImageRequest {
    private long providerId;
    private MultipartFile idCard;
    private MultipartFile image;

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public MultipartFile getIdCard() {
        return idCard;
    }

    public void setIdCard(MultipartFile idCard) {
        this.idCard = idCard;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
