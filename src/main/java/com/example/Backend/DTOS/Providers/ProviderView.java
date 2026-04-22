package com.example.Backend.DTOS.Providers;

public class ProviderView {
    private ProviderDetail providerDetail;
    private ProviderAnalytics analytics;
    private ProviderInformation information;
    private ServiceInformation serviceInformation;

    public ProviderView(ProviderDetail providerDetail, ProviderAnalytics analytics, ProviderInformation information, ServiceInformation serviceInformation) {
        this.providerDetail = providerDetail;
        this.analytics = analytics;
        this.information = information;
        this.serviceInformation = serviceInformation;
    }

    public ProviderDetail getProviderDetail() {
        return providerDetail;
    }

    public void setProviderDetail(ProviderDetail providerDetail) {
        this.providerDetail = providerDetail;
    }

    public ProviderAnalytics getAnalytics() {
        return analytics;
    }

    public void setAnalytics(ProviderAnalytics analytics) {
        this.analytics = analytics;
    }

    public ProviderInformation getInformation() {
        return information;
    }

    public void setInformation(ProviderInformation information) {
        this.information = information;
    }

    public ServiceInformation getServiceInformation() {
        return serviceInformation;
    }

    public void setServiceInformation(ServiceInformation serviceInformation) {
        this.serviceInformation = serviceInformation;
    }
}
