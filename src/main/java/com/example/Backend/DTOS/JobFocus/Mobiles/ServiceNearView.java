package com.example.Backend.DTOS.JobFocus.Mobiles;

public class ServiceNearView {
    private Long id;
    private String name;
    private String service;
    private Double price;
    private Double km;
    private String image;

    public ServiceNearView(Long id, String name, String service, Double price, Double km, String image) {
        this.id = id;
        this.name = name;
        this.service = service;
        this.price = price;
        this.km = km;
        this.image = image;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
