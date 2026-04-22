package com.example.Backend.Requests;

public class AddressProviderRequest {
    private Long providerId;
    private  Double lat;
    private  Double lon;
    private int serviceRadius;

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
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

    public int getServiceRadius() {
        return serviceRadius;
    }

    public void setServiceRadius(int serviceRadius) {
        this.serviceRadius = serviceRadius;
    }
}
