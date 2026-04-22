package com.example.Backend.DTOS.MobileProviders.Homes;

import com.example.Backend.DTOS.AddressDto;

public class NewRequest {
    private Long id;
    private String name;
    private String image;
    private String price;
    private String requested;
    private AddressDto address;

    public NewRequest(Long id, String name, String image, String price, String requested, AddressDto address) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.requested = requested;
        this.address = address;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRequested() {
        return requested;
    }

    public void setRequested(String requested) {
        this.requested = requested;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
