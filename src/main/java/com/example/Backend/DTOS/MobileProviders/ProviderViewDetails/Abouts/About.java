package com.example.Backend.DTOS.MobileProviders.ProviderViewDetails.Abouts;

import com.example.Backend.DTOS.AddressDto;

import java.util.List;

public class About {
    private String about;
    private String tel;
    private AddressDto address;
    private WorkingInfo workingInfo;
    private String email;
    private List<String> services;

    public About(String about, String tel, AddressDto address, WorkingInfo workingInfo, List<String> services,String email) {
        this.about = about;
        this.tel = tel;
        this.address = address;
        this.workingInfo = workingInfo;
        this.services = services;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public WorkingInfo getWorkingInfo() {
        return workingInfo;
    }

    public void setWorkingInfo(WorkingInfo workingInfo) {
        this.workingInfo = workingInfo;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
