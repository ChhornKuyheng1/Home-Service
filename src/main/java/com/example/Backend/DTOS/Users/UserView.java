package com.example.Backend.DTOS.Users;

import com.example.Backend.DTOS.AddressDto;

import java.time.LocalDate;

public class UserView {
    private String name;
    private String gender;
    private String tel;
    private LocalDate dateOfBirth;
    private String JoinDate;
    private AddressDto address;
    private String image;

    public UserView(String name, String gender, String tel, LocalDate dateOfBirth, String joinDate, AddressDto address, String image) {
        this.name = name;
        this.gender = gender;
        this.tel = tel;
        this.dateOfBirth = dateOfBirth;
        JoinDate = joinDate;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
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
