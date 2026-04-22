package com.example.Backend.DTOS.Categorys;

public class CategoryDto {
    private Long id;
    private String name;
    private Long provider;
    private Long booking;
    private String status;
    private String  image;

    public CategoryDto(Long id, String name, Long provider, Long booking, String status, String image) {
        this.id = id;
        this.name = name;
        this.provider = provider;
        this.booking = booking;
        this.status = status;
        this.image = image;
    }

    public Long getId() {
        return id;
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

    public Long getProvider() {
        return provider;
    }

    public void setProvider(Long provider) {
        this.provider = provider;
    }

    public Long getBooking() {
        return booking;
    }

    public void setBooking(Long booking) {
        this.booking = booking;
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
