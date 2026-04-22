package com.example.Backend.DTOS.Skills;

public class SkillAdminDto {
    private Long id;
    private String name;
    private String serviceName;
    private Long serviceId;
    private Long provider;
    private Long booking;
    private String image;

    public SkillAdminDto(Long id, String name, String serviceName, Long provider, Long booking, String image,Long serviceId) {
        this.id = id;
        this.name = name;
        this.serviceName = serviceName;
        this.provider = provider;
        this.booking = booking;
        this.image = image;
        this.serviceId = serviceId;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
