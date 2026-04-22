package com.example.Backend.DTOS.MobileProviders.Jobs;

public class UserBookingView {
    private String name;
    private String tel;
    private String image;

    public UserBookingView(String name, String tel, String image) {
        this.name = name;
        this.tel = tel;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
