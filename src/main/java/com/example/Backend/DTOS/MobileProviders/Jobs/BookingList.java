package com.example.Backend.DTOS.MobileProviders.Jobs;

import com.example.Backend.DTOS.AddressDto;

public class BookingList {
    private Long id;
    private String serviceName;
    private String serviceImage;
    private String skillName;
    private String skillImage;
    private String price;
    private String userName;
    private String status;
    private AddressDto address;

    public BookingList(Long id, String serviceName, String serviceImage, String skillName, String skillImage, String price, String userName, String status, AddressDto address) {
        this.id = id;
        this.serviceName = serviceName;
        this.serviceImage = serviceImage;
        this.skillName = skillName;
        this.skillImage = skillImage;
        this.price = price;
        this.userName = userName;
        this.status = status;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillImage() {
        return skillImage;
    }

    public void setSkillImage(String skillImage) {
        this.skillImage = skillImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
