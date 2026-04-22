package com.example.Backend.DTOS.Providers;

import com.example.Backend.DTOS.AddressDto;

import java.time.LocalDate;
import java.util.List;

public class ProviderInformation {
    private String name;
    private String gender;
    private int age;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;
    private AddressDto address;
    private List<String> document;

    public ProviderInformation(String name, String gender, int age, String phone, String email, LocalDate dateOfBirth, AddressDto address, List<String> document) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.document = document;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<String> getDocument() {
        return document;
    }

    public void setDocument(List<String> document) {
        this.document = document;
    }
}
