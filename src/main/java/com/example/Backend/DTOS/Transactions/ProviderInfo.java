package com.example.Backend.DTOS.Transactions;

import com.example.Backend.DTOS.AddressDto;

import java.time.LocalDate;

public class ProviderInfo {
    private String name;
    private String tel;
    private String email;
    private LocalDate dob;
    private AddressDto address;
    private String image;
    public ProviderInfo(String name, String tel, String email, LocalDate dob, AddressDto address,String image) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.dob = dob;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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
