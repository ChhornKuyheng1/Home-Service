package com.example.Backend.DTOS;

public class AdminDto {
    private String name;
    private String gender;
    private String tel;
    private String email;
    private String image;

    public AdminDto(String name, String gender, String tel, String email, String image) {
        this.name = name;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
