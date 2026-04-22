package com.example.Backend.DTOS;

public class Contact {
    private String tel;
    private String email;

    public Contact(String tel, String email) {
        this.tel = tel;
        this.email = email;
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
}
