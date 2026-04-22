package com.example.Backend.DTOS.Users;

import com.example.Backend.DTOS.AddressDto;

import java.time.LocalDate;

public class UserList {
    private Long id;
    private String name;
    private String tel;
    private String gender;
    private String joinDate;
    private String image;
    private  String address;
    public UserList(Long id, String name, String tel,String gender, String joinDate, String image,String address) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.gender = gender;
        this.joinDate = joinDate;
        this.image = image;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
