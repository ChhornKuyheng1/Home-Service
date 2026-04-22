package com.example.Backend.DTOS.MobileProviders.Homes;

import com.example.Backend.DTOS.AddressDto;

public class JobToDay {
    private Long id;
    private String jobName;
    private String userName;
    private String price;
    private String tel;
    private AddressDto address;
    private String started;
    private String image;
    public Long getId() {
        return id;
    }

    public JobToDay(Long id, String jobName, String userName, String price, String tel, AddressDto address, String started, String image) {
        this.id = id;
        this.jobName = jobName;
        this.userName = userName;
        this.price = price;
        this.tel = tel;
        this.address = address;
        this.started = started;
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
