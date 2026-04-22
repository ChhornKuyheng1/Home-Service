package com.example.Backend.DTOS.MobileProviders.Homes;

import java.util.List;

public class ProviderHomePage {
    private ProviderAccount account;
    private List<NewRequest> newRequest;
    private List<JobToDay> job;
    private List<Completed> completed;

    public ProviderHomePage(ProviderAccount account, List<NewRequest> newRequest, List<JobToDay> job, List<Completed> completed) {
        this.account = account;
        this.newRequest = newRequest;
        this.job = job;
        this.completed = completed;
    }

    public ProviderAccount getAccount() {
        return account;
    }

    public void setAccount(ProviderAccount account) {
        this.account = account;
    }

    public List<NewRequest> getNewRequest() {
        return newRequest;
    }

    public void setNewRequest(List<NewRequest> newRequest) {
        this.newRequest = newRequest;
    }

    public List<JobToDay> getJob() {
        return job;
    }

    public void setJob(List<JobToDay> job) {
        this.job = job;
    }

    public List<Completed> getCompleted() {
        return completed;
    }

    public void setCompleted(List<Completed> completed) {
        this.completed = completed;
    }
}
