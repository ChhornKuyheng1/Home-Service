package com.example.Backend.DTOS.AdminDashboards;

public class ProviderRequestViewDashboard {
    private Long id;
    private String name;
    private String  tel;
    private String image;
    private String email;
    public ProviderRequestViewDashboard(Long id, String name, String tel, String image,String eamil) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.image = image;
        this.email = eamil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
