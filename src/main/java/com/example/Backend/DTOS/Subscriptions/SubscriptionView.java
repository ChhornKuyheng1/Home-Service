package com.example.Backend.DTOS.Subscriptions;

public class SubscriptionView {
    private SubscriptionInfo subscriptionInfo;
    private ProviderInfo providerInfo;
    private BillingInfo billingInfo;

    public SubscriptionView(SubscriptionInfo subscriptionInfo, ProviderInfo providerInfo, BillingInfo billingInfo) {
        this.subscriptionInfo = subscriptionInfo;
        this.providerInfo = providerInfo;
        this.billingInfo = billingInfo;
    }

    public SubscriptionInfo getSubscriptionInfo() {
        return subscriptionInfo;
    }

    public void setSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        this.subscriptionInfo = subscriptionInfo;
    }

    public ProviderInfo getProviderInfo() {
        return providerInfo;
    }

    public void setProviderInfo(ProviderInfo providerInfo) {
        this.providerInfo = providerInfo;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }
}
