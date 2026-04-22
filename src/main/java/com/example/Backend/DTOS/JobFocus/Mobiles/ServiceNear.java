package com.example.Backend.DTOS.JobFocus.Mobiles;

public class ServiceNear {
    private Long id;
    private String name;
    private String image;
    private Long provider; // count of providers
    private Double price;

    public ServiceNear(Long id, String name, String image, Long provider, Double price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.provider = provider;
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getProvider() {
        return provider;
    }

    public void setProvider(Long provider) {
        this.provider = provider;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
