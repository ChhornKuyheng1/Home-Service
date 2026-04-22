package com.example.Backend.DTOS.MobileProviders.Profiles;

import com.example.Backend.DTOS.AddressDto;
import com.example.Backend.DTOS.Contact;
import com.example.Backend.DTOS.JobFocus.Mobiles.TopSkill;

import java.util.List;

public class Personal {
    private String businessName;
    private String description;
    private long serviceRadius;
    private List<TopSkill> topSkills;
    private Contact contact;
    private AddressDto address;

    public Personal(String businessName, String description, long serviceRadius, List<TopSkill> topSkills, Contact contact, AddressDto address) {
        this.businessName = businessName;
        this.description = description;
        this.serviceRadius = serviceRadius;
        this.topSkills = topSkills;
        this.contact = contact;
        this.address = address;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getServiceRadius() {
        return serviceRadius;
    }

    public void setServiceRadius(long serviceRadius) {
        this.serviceRadius = serviceRadius;
    }

    public List<TopSkill> getTopSkills() {
        return topSkills;
    }

    public void setTopSkills(List<TopSkill> topSkills) {
        this.topSkills = topSkills;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
