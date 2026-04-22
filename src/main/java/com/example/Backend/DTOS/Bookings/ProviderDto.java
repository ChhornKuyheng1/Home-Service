package com.example.Backend.DTOS.Bookings;

import com.example.Backend.DTOS.AddressDto;

public class ProviderDto {
    private String name;
    private String tel;
    private String email;
    private AddressDto address;
    private String image;
    private double rate;

    public ProviderDto(String name, String tel, String email, AddressDto address, String image, double rate) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.image = image;
        this.rate = rate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
