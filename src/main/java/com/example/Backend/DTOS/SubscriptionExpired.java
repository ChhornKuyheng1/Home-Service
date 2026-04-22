package com.example.Backend.DTOS;

public class SubscriptionExpired {

    private  boolean expired;

    public SubscriptionExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isExpired() {
        return expired;
    }
}
