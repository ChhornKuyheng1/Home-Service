package com.example.Backend.DTOS.Bookings;

import com.example.Backend.DTOS.AddressDto;

public class UserDto {
    private String name;
    private String tel;
    private AddressDto address;
    private String image;

    public UserDto(String name, String tel, AddressDto address, String image) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.image = image;
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
}
