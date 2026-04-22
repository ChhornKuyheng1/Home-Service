package com.example.Backend.DTOS.Jobs;

public class ProviderJob {
    private long id;
    private String name;
    private String status;
    private String image;

    public ProviderJob(long id, String name, String status, String image) {
        this.id = id;
        this.name = name;
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
