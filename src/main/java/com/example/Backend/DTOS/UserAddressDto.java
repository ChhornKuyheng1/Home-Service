package com.example.Backend.DTOS;

public class UserAddressDto {
    private Long id;
    private String  name;
    private String status;
    private Double lat;
    private Double lon;

    public UserAddressDto(Long id, String name, String status, Double lat, Double lon) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.lat = lat;
        this.lon = lon;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
